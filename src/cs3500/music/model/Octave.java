package cs3500.music.model;

/**
 * An Enumeration of the valid/allowable Octaves that a note can have.
 */
public enum Octave {
  /**
   * The allowable values I designate as the ten octaves humans can hear
   * - starting at one as the lowest and ending at ten as the highest tone.
   */
  ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);

  /**
   * The integer representing this specific Octave.
   */
  private final int octaveNumber;

  /**
   * Constructs an Octave given an integer value - storing the specified octave number.
   *
   * @param octNum the given int octaveNumber
   */
  Octave(int octNum) {
    this.octaveNumber = octNum;
  }

  /**
   * Returns the Octave representing the provided int value.
   *
   * @param number the provided int value
   * @return the Octave specified
   * @throws IllegalArgumentException if the provided int value doesn't represent to a valid Octave
   */
  public static Octave fromInt(int number) {
    switch (number) {
      case 1:
        return ONE;
      case 2:
        return TWO;
      case 3:
        return THREE;
      case 4:
        return FOUR;
      case 5:
        return FIVE;
      case 6:
        return SIX;
      case 7:
        return SEVEN;
      case 8:
        return EIGHT;
      case 9:
        return NINE;
      case 10:
        return TEN;
      default:
        throw new IllegalArgumentException("Invalid Octave - Given: " + number);
    }
  }

  /**
   * Returns a String representing this Octave (an int as a String).
   *
   * @return the number representing this Octave as a String
   */
  public String toString() {
    return String.valueOf(octaveNumber);
  }

  /**
   * A getter for the stored octaveNumber (as an int).
   *
   * @return the number representing this specific type of Octave
   */
  public int toInt() {
    return this.octaveNumber;
  }


  /**
   * Returns an Octave one value Higher.
   *
   * @param octave the given octave
   * @return an octave one value higher
   */
  public static Octave oneHigher(Octave octave) {
    switch (octave.toInt()) {
      case 1:
        return TWO;
      case 2:
        return THREE;
      case 3:
        return FOUR;
      case 4:
        return FIVE;
      case 5:
        return SIX;
      case 6:
        return SEVEN;
      case 7:
        return EIGHT;
      case 8:
        return NINE;
      case 9:
        return TEN;
      case 10:
        throw new IllegalArgumentException(
                "Can't raise this value, something went wrong: " + octave);
      default:
        throw new IllegalArgumentException("Invalid Octave - Given: " + octave.toInt());
    }
  }
}
