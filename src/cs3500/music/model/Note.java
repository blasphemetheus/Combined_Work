package cs3500.music.model;

import java.util.Objects;

/**
 * Represents a Note, which has pitch, octave, duration and volume.
 */
public class Note {


  //private double volume;

  private Pitch pitch;

  private Octave octave;

  /**
   * From the documentation: A note’s duration is measured in beats,
   * and can in general be any integer multiple of any integer power of two beats long.
   */
  private Duration duration;

  /**
   * Constructs a Note.
   *
   * @param pitch    the given pitch
   * @param octave   the given octave
   * @param duration the given int duration
   */
  Note(Pitch pitch, Octave octave, Duration duration) {
    Objects.requireNonNull(pitch);
    Objects.requireNonNull(octave);
    // default volume is 1 (range is from 0 to 1, can be any double to two decimal points)
    //this.volume = 1.0;

    this.pitch = pitch;

    this.octave = octave;

    // Duration is already validated (soundness) in its own Constructor
    this.duration = duration;
  }

  public String toString() {
    return "" + this.pitch.toString() + this.octave.toString();
  }

  public Duration getDur() {
    return this.duration;
  }

  /**
   * Gets the pitch.
   *
   * @return the Pitch
   */
  public Pitch getPitch() {
    return pitch;
  }

  /**
   * Gets the octave.
   *
   * @return the octave
   */
  public Octave getOctave() {
    return octave;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Note)) {
      return false;
    }

    Note note = (Note) o;

    if (duration != note.duration) {
      return false;
    }
    if (pitch != note.pitch) {
      return false;
    }
    return octave == note.octave;
  }

  @Override
  public int hashCode() {
    int result = pitch.hashCode();
    result = 31 * result + octave.hashCode();
    result = 31 * result + duration.hashCode();
    return result;
  }

  /**
   * Returns whether this Note is higher than the given Note.
   *
   * @param thatNote the given Note
   * @return a boolean representing whether this is higher than thatNote
   */
  public boolean isHigher(Note thatNote) {
    Objects.requireNonNull(thatNote);

    // that > this
    if (thatNote.getOctave().toInt() > this.getOctave().toInt()) {
      return false;
    } else {
      // that < this
      if (thatNote.getOctave().toInt() < this.getOctave().toInt()) {
        return true;
      } else {
        // octaves are equal
        return thatNote.getPitch().getOrderVal() < this.getPitch().getOrderVal();
      }
    }
  }

  /**
   * Returns whether this Note is lower than the given Note.
   *
   * @param that the given Note
   * @return whether this is lower than that Note
   */
  public boolean isLower(Note that) {
    Objects.requireNonNull(that);

    // that > this
    if (that.getOctave().toInt() > this.getOctave().toInt()) {
      return true;
    } else {
      // that < this
      if (that.getOctave().toInt() < this.getOctave().toInt()) {
        return false;
      } else {
        // octaves are equal
        return that.getPitch().getOrderVal() > this.getPitch().getOrderVal();
      }
    }

  }

  /**
   * Sets where the Note starts.
   *
   * @param beatStart the int where the Note starts.
   */
  public void setBeatStart(int beatStart) {
    this.duration.setStartBeat(beatStart);
  }

  /**
   * Returns where the Note starts.
   *
   * @return the int where the Note starts
   */
  public int getBeatStart() {
    return this.duration.getStartBeat();
  }

  /**
   * Returns a copy of this note just one value higher in Tone (same duration).
   *
   * @return a copy of this note one note higher
   */
  public Note getOneHigher() {
    Octave octave = this.getOctave();
    if (this.pitch == Pitch.G$A) {
      octave = Octave.oneHigher(this.getOctave());
    }
    Note newNote = new Note(Pitch.oneHigher(this.getPitch()), octave, this.getDur());
    return newNote;
  }

  /**
   * Returns the beatStart of this Note.
   */
  public BeatState getBeatState(int currentBeat) {
    if (currentBeat < 0) {
      throw new IllegalStateException("Invalid current beat to have: " + currentBeat);
    }

    int thisNotesBeat = this.getDur().getStartBeat();
    int thisNotesEndBeat = this.getDur().getEndBeat();

    if (thisNotesBeat > currentBeat || currentBeat > thisNotesEndBeat) {
      return BeatState.REST;
    } else {
      if (thisNotesBeat == currentBeat) {
        return BeatState.ONSET;
      } else {
        return BeatState.SUSTAIN;
      }
    }
  }
}
