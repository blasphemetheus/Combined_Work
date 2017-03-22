package cs3500.music.model;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

import cs3500.music.util.*;

/**
 * Represents the model for an instance of the Music Editor.
 */
public final class MusicModel implements ModelOperations {
  // To hold our notes
  private List<Note> storedNotes;
  // to hold the instrument
  private Instrument instrument;
  // to hold the tempo
  private int tempo;

  // Helps figure out the length of this model
  //private int currentLength;

  // rip hashtable/map method of storing notes

  // Stores stuff for save and retrieve
  Stack<List<Note>> stackTrack;

  /**
   * Constructs a MusicModel (storing the default Meter and Track).
   */
  public MusicModel() {
    this.storedNotes = new ArrayList<>();
    this.stackTrack = new Stack<List<Note>>();
    this.instrument = Instrument.SINE;
    this.tempo = 10000;
  }

  @Override
  public void edit(Note input, Note output) {
    for (Note note : storedNotes) {
      if (note.equals(input)) {
        note = new Note(output.getPitch(), output.getOctave(), output.getDur());
      } else {
        throw new IllegalArgumentException("Note: " + input + "does not exist in this model");
      }
    }
  }

  @Override
  public List<Note> getNotes() {
    return this.storedNotes;
  }

  @Override
  public void changeInstrument(Instrument instrument) {
    Objects.requireNonNull(instrument);
    this.instrument = instrument;
  }

  @Override
  public int numBeats() {
    List<Integer> noteEndings = new ArrayList<>();

    for (Note note: storedNotes) {
      noteEndings.add(note.getDur().getEndBeat());
    }

    int last = Collections.max(noteEndings);

    return last;
  }

  @Override
  public Note getLowestNote() throws IllegalArgumentException {

    Note lowest = null;

    for (Note note: storedNotes) {
      if (lowest == null) {
        lowest = note;
      } else {
        if (note.isLower(lowest)) {
          lowest = note;
        }
      }
    }

    if (lowest == null) {
      throw new IllegalArgumentException("there are no lowest notes - there are no notes");
    }

    return lowest;
  }

  @Override
  public Note getHighestNote() throws IllegalArgumentException {
    Note highest = null;

    for (Note note: storedNotes) {
      if (highest == null) {
        highest = note;
      } else {
        if (note.isHigher(highest)) {
          highest = note;
        }
      }
    }

    if (highest == null) {
      throw new IllegalArgumentException("there are no highest notes - there are no notes");
    }

    return highest;
  }

  @Override
  public void overwriteWith(ModelOperations thatTrack) {
    Objects.requireNonNull(thatTrack);
    Objects.requireNonNull(thatTrack.getNotes());

    this.storedNotes = thatTrack.getNotes();
    this.instrument = thatTrack.getInstrument();
    this.tempo = thatTrack.getTempo();
  }

  @Override
  public void startEditor() {
    this.overwriteWith(new MusicModel());
  }

  @Override
  public void save() {
    stackTrack.push(this.storedNotes);
  }

  @Override
  public void retrieve() throws IllegalStateException {
    if (stackTrack.isEmpty()) {
      throw new IllegalStateException("No Track to Retrieve");
    }
    this.storedNotes = this.stackTrack.pop();
  }

  @Override
  public void setTempo(int microsecondsPerBeat) {
    if (microsecondsPerBeat <= 0) {
      throw new IllegalArgumentException("Invalid Tempo (in microseconds per beat: "
              + microsecondsPerBeat);
    }

    this.tempo = microsecondsPerBeat;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

   @Override
  public void addNote(Note note) throws IllegalArgumentException {
    Objects.requireNonNull(note);
    Duration noteDur = note.getDur();

    if (this.storedNotes.isEmpty()) {
      this.storedNotes.add(note);

    } else {


      boolean contains = true;
      for (int i = 0; i < this.storedNotes.size(); i++) {
        if (this.storedNotes.get(i).equals(note)) {

          contains = false;
        }
      }
        if (contains) {
          this.storedNotes.add(note);
        } else {
          throw new IllegalArgumentException("Note already exists");
        }
      }
  }

  @Override
  public void removeNote(Note note) throws IllegalArgumentException {
    Objects.requireNonNull(note);

    if (storedNotes.contains(note)) {
      for (Note currentNote : storedNotes) {
        if (currentNote.equals(note)) {
          storedNotes.remove(note);
          break;
          // after it removes the intended note is stops searching
        }

      }
    } else {
      throw new IllegalArgumentException("Note:" + note + "does not exist");
    }
  }

  @Override
  public void removeAllOf(Pitch pitch, Octave octave) {
    Objects.requireNonNull(pitch);
    Objects.requireNonNull(octave);

    List<Note> notesToDestroy = new ArrayList<>();

    for (Note note : storedNotes) {
      if (note.getPitch() == pitch && note.getOctave() == octave) {
        storedNotes.remove(note);
      }
    }
  }

  @Override
  public void mergeWith(ModelOperations thatTrack) {
    Objects.requireNonNull(thatTrack);
    this.storedNotes.addAll(thatTrack.getNotes());
  }

  @Override
  public void combineConsecutively(ModelOperations thatTrack) {
    Objects.requireNonNull(thatTrack);

    int last = this.numBeats();

    for (Note note : storedNotes) {
      Duration d = note.getDur();
      d.setStartBeat(last + d.getStartBeat());
      storedNotes.add(note);
    }
  }

  @Override
  public List<Note> getAllPlayingAtBeat(int beat) {
    List<Note> output = new ArrayList<>();

    for (Note note : storedNotes) {
      Duration dur = note.getDur();

      if (dur.getStartBeat() <= beat && dur.getEndBeat() >= beat) {
        output.add(note);
      }
    }
    return output;
  }
  
  @Override
  public List<Note> getAllStartingAtBeat(int beat) {
    List<Note> startingAt = this.getAllPlayingAtBeat(beat);

    for (Note note: storedNotes) {
      Duration dur = note.getDur();

      if (dur.getStartBeat() == beat) {
        startingAt.add(note);
      }
    }
    return startingAt;
  }

  @Override
  public boolean isEmpty() {
    return this.storedNotes.isEmpty();
  }

  @Override
  public Instrument getInstrument() {
    return this.instrument;
  }

  @Override
  public Map<Integer, List<Note>> getMap() {
    return null;
  }

  /**
   * The builder class for my MusicModel.
   */
  public static final class Builder implements CompositionBuilder<ModelOperations> {
    List<Note> listOfNotes;
    int tempo;

    private Builder() {
      listOfNotes = new ArrayList<>();
      tempo = -1;
    }


    /**
     * Constructs an actual composition, given the notes that have been added.
     *
     * @return The new composition
     */
    @Override
    public ModelOperations build() {

      Builder builder = new Builder();

      ModelOperations model = new MusicModel();

      if (tempo == -1) {
        throw new IllegalArgumentException("Didn't setTempo silly, so it's set at dummy value -1");
      }

      model.setTempo(this.tempo);

      for (Note note : listOfNotes) {
        model.addNote(note);
      }

      return model;
    }

    /**
     * Sets the tempo of the piece.
     *
     * @param tempo The speed, in microseconds per beat
     * @return This builder
     */
    @Override
    public CompositionBuilder<ModelOperations> setTempo(int tempo) {
      this.tempo = tempo;
      return this;
    }

    /**
     * Adds a new note to the piece.
     *
     * @param start The start time of the note, in beats
     * @param end The end time of the note, in beats
     * @param instrument The instrument number (to be interpreted by MIDI)
     * @param pitch The pitch (in the range [0, 127], where 60 represents C4, the middle-C on a piano)
     * @param volume The volume (in the range [0, 127])
     * @return
     */
    @Override
    public CompositionBuilder<ModelOperations> addNote(int start, int end, int instrument,
                                                       int pitch, int volume) {
      
      return this;
    }
    // FILL IN HERE
  }
}
