package cs3500.music.model;

/**
 * * The Duration is when the note starts till when the note ends. The length of a note is
 * when it ends negated by when it starts. A note is measured in beats. 4 beats make a measure.
 * The Duration can only be positive integers for now so startAt and endAt cannot be negative.
 * Furthermore the music is progressing so it cannot have an end point before its start point.
 *
 */
public class Duration {
  // DEFAULT IS -1 UNLESS PLACED, THEN IS WHATEVER PLACED
  private int startBeat;
  private int beatsHeld;

  public Duration(int startBeat, int beatsHeld) {
    this.startBeat = startBeat;
    this.beatsHeld = beatsHeld;
    this.validateBeatsHeld(beatsHeld);
    this.validateStartBeat(startBeat);
  }

  public void setBeatsHeld(int newBeatsHeld) {
    this.validateBeatsHeld(newBeatsHeld);
    this.beatsHeld = newBeatsHeld;
  }

  /**
   * Sets the
   * @param newStartBeat
   */
  public void setStartBeat(int newStartBeat) throws IllegalArgumentException {
    validateStartBeat(newStartBeat);
    this.startBeat = newStartBeat;
  }

  /**
   * Returns the startBeat of this duration.
   * @return the int startBeat
   */
  public int getStartBeat() {
    return this.startBeat;
  }

  /**
   * Returns the endBeat of this duration.
   * @return the int endBeat
   */
  public int getEndBeat() {
    return this.startBeat + (beatsHeld - 1);
  }

  /**
   * Returns the number of beats held of this duration.
   * @return the int beatsHeld
   */
  public int getBeatsHeld() {
    return this.beatsHeld;
  }

  /**
   * Validates the beatsHeld (Must always be held at least one beat).
   */
  private void validateBeatsHeld(int beatsH) {
    if (beatsH <= 0) {
      throw new IllegalArgumentException("Invalid time: " + beatsH);
    }
  }

  /**
   * Validates the startBeat int (Must always be 0 or greater).
   */
  private void validateStartBeat(int start) {
    if (start < 0) {
      throw new IllegalArgumentException("Invalid startBeat: " + start);
    }
  }

}
