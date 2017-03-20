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
  private List<Note> storedNotes = new ArrayList<Note>();
  private Instrument instrument;

  // Helps figure out the length of this model
  //private int currentLength;

  // rip hashtable/map method of storing notes

  // Stores stuff for save and retrieve
  Stack<List<Note>> stackTrack;

  /**
   * Constructs a MusicModel (storing the default Meter and Track).
   */
  public MusicModel() {
    this.startEditor();
    this.stackTrack = new Stack<List<Note>>();
    this.instrument = Instrument.SINE;
    //currentLength = 0;
  }

//  @Override
//  public void edit(Note x, Note y) {
//    for (int i = 0; i < Music.size(); i++) {
//      if (Music.get(i) == x) {
//        Music.get(i).pitch = y.pitch;
//        Music.get(i).octave = y.octave;
//        Music.get(i).dur = y.dur;
//      } else {
//        throw new IllegalArgumentException("Note:" + x + "does not exist");
//      }
//    }
//  }

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
  public void addNote(Note note) throws IllegalArgumentException {
    Objects.requireNonNull(note);
    Duration noteDur = note.getDur();
    // don't have to validate because the Duration is sound (def valid)

    boolean contains = true;
    for (int i = 0; i < storedNotes.size() ; i++) {
      if (storedNotes.get(i).equals(note)) {
        contains = false;
      }

      if (contains) {
        storedNotes.add(note);
      } else {
        throw new IllegalArgumentException("Note already exists");
      }
    }
  }

  @Override
  public void edit(Note x, Note y) throws IllegalArgumentException {

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

    @Override
    public ModelOperations build() {

      ModelOperations model = new MusicModel();
      return null;
    }

    @Override
    public CompositionBuilder<ModelOperations> setTempo(int tempo) {
      return null;
    }

    @Override
    public CompositionBuilder<ModelOperations> addNote(int start, int end, int instrument, int pitch, int volume) {
      return null;
    }
    // FILL IN HERE
  }
}
