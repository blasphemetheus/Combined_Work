package cs3500.music.model;

/**
 * An enumeration of the different states a Beat can be in.
 */
public enum BeatState {
  REST(" "), ONSET("X"), SUSTAIN("|");

  private String myString;

  /**
   * Constructs a BeatState passing in a String to represent the type of state.
   *
   * @param myString the String representation
   */
  BeatState(String myString) {
    this.myString = myString;
  }

  /**
   * Gets the String associated with this BeatState.
   *
   * @return the String representation of this BeatState
   */
  String getMyString() {
    return this.myString;
  }
}
