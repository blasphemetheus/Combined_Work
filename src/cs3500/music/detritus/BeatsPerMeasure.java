package cs3500.music.detritus;

/**
 * An enumeration of the allowable number of beats to have per measure.
 * I am arbitrarily capping the allowed number of beats at 9.
 */
public enum BeatsPerMeasure {
  ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9);

  private int storedInt;

  /**
   * Constructs a BeatsPerMeasure with a corresponding integer value being stored.
   *
   * @param integerVal the int value of the BPMeasure
   */
  BeatsPerMeasure(int integerVal) {
    this.storedInt = integerVal;
  }

  /**
   * Returns the stored int.
   * @return the stored int
   */
  public int getInt() {
    return this.storedInt;
  }
}
