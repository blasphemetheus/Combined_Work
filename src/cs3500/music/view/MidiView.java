package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

/**
 * Created by blewf on 3/18/2017.
 */
public class MidiView implements ViewOperations {
  private final Synthesizer synth;
  private final Receiver receiver;

  /**
   * The default constructor for the MidiView.
   */
  public MidiView() {
    try {
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
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
   */
  public void playNote() throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 64);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 64);
    this.receiver.send(start, -1);
    this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);

    /*
    The receiver does not "block", i.e. this method
    immediately moves to the next line and closes the
    receiver without waiting for the synthesizer to
    finish playing.

    You can make the program artificially "wait" using
    Thread.sleep. A better solution will be forthcoming
    in the subsequent assignments.
    */
    this.receiver.close(); // Only call this once you're done playing *all* notes
  }



  ///////////////////////////////////////////////////////////////////////////////////


  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void setListeners(ActionListener clicks, KeyListener keys) {
    this.addKeyListener(keys);
    this.echoButton.addActionListener(clicks);
    this.exitButton.addActionListener(clicks);
  }


  /**
   * A method that deals with showing that keyboard yo.
   */
  public void showKeyboard() {
    //shows keyboard

    for (int i = 0; i < 10; i++) {
      this.buildOctave();

    }

  }

  /**
   *
   */
  public void buildNatural() {

  }

  /**
   * Puts a block not
   */
  public void buildAccidental() {

  }

  public void
}
