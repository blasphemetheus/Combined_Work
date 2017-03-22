package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.controller.ButtonListener;
import cs3500.music.controller.KeyboardListener;
import cs3500.music.model.ModelOperations;
import cs3500.music.model.Note;

/**
 * The view that renders as audio (via MIDI) playback.
 */
public class MidiView implements ViewOperations {
  ModelOperations model;
  private Synthesizer synth;
  private Receiver receiver;
  private Sequencer sequencer;


  /**
   * The default constructor for the MidiView.
   */
  public MidiView() {
    try {
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.synth.open();
      this.sequencer = MidiSystem.getSequencer();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    //TODO decide if we even want this constructor
    this.model = null;
  }

  /**
   * The default constructor for the MidiView.
   */
  public MidiView(ModelOperations model) {
    try {
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.synth.open();
      this.sequencer = MidiSystem.getSequencer();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.model = model;
  }

  /**
   * Permanently ends playback.
   */
  public void endPlayback() {
    this.receiver.close();
  }

  /**
   * Renders the MidiView by playing through a piece of music (at the tempo specified in the model).
   */
  @Override
  public void render() {
    List<Note> listOfNotes = model.getNotes();

    System.out.println("play song now");

    for (Note note: listOfNotes) {
      try {
        this.playNote(note, model.getTempo());
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }

    int lengthInBeats = model.numBeats();
    int tempo = model.getTempo();
    int lengthInMicroSeconds = lengthInBeats * tempo;

    try {
      Thread.sleep(lengthInMicroSeconds);
    } catch (InterruptedException e) {
      System.out.println("Interrupted Yo");
      e.printStackTrace();
    }
  }

  /**
   * Plays a Note as a MIDI thing turned into sound.
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
   *
   * @param note the given note to be played
   * @param tempo the tempo in microseconds
   * @throws InvalidMidiDataException
   */
  protected void playNote(Note note, int tempo) throws InvalidMidiDataException {
    //    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 64);
    //    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 64);
    //    this.receiver.send(start, -1);
    //    this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);

    // My shortmessage protocol is (all int) (statusByte, channel, pitch, volume)
    MidiMessage initiateNote = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument(),
            note.getPitch(), note.getVolume());
    MidiMessage endNote = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument(),
            note.getPitch(), note.getVolume());


    // need to change the beat value the note stores to microseconds (uses tempo)
    // TODO change to get rid of errors
    int startTiming = this.convertBeatToMicrosecond(note.getDur().getStartBeat(), tempo);
    // TODO change this to not be an error (logic too?)
    int endTiming = (int) this.synth.getMicrosecondPosition() + tempo
            * (note.getDur().getBeatsHeld() - 1);
    this.receiver.send(initiateNote, startTiming);
    this.receiver.send(endNote, endTiming);this.synth.getMicrosecondPosition() + tempo * note.getDur())
  }

  /**
   * Returns the toString of the receiver.
   * @return a string representation of the current Midi view reciever object
   */
  public String receiverString() {
    return this.receiver.toString();
  }

  /**
   * Plays all the notes that start at the specified beat.
   * 
   * @param beat the specified beat
   */
  public void playAllStartingAtBeat(int beat) {
    List<Note> startingAtBeat = model.getAllStartingAtBeat(beat);

    for (Note note: startingAtBeat) {
      try {
        playNote(note, model.getTempo());
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void addKeyListener(KeyboardListener kbd) {

  }

  @Override
  public void addActionListener(ButtonListener buttonListener) {
  }

  ///////////////////////////////////////////////////////////////////////////////////

  /*
  You must implement either a builder or convenience constructors for your MIDI view,
  so that by default the view uses the actual MIDI synthesizer, but for testing can be run
  with your mock instead. If you create a StringBuilder, and pass to the mock-synth,
  you can then read out the contents of the StringBuilder to confirm that you’ve
  played all the right notes.

  Hint: Remember that you are not testing whether Java’s Receiver, MidiDevice,
  etc. are working correctly: they do. You are testing whether your program is
  using them correctly to provide the correct inputs to these classes so that
  they may play them.
   */

  /**
   * The Buider for my MIDI view so that the view can use the actual MIDI synth for real but then
   * use my mocks instead.
   */
  public static final class Builder {
    
    //TODO


  }
}
