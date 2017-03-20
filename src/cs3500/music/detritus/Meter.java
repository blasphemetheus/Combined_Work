package cs3500.music.detritus;

/**
 * Enumerates the allowable time signatures, or meters,
 * keeping to the enumerated BeatsPerMeasure and BeatType
 * (corresponding to the top and bottom numbers in a time signature).
 */
public enum Meter {
  /**
   * The following types of meter simply hold the allowable time signatures.
   */
  ONE_FOUR(BeatsPerMeasure.ONE, BeatType.QUARTER),
  TWO_FOUR(BeatsPerMeasure.TWO, BeatType.QUARTER),
  THREE_FOUR(BeatsPerMeasure.THREE, BeatType.QUARTER),
  FOUR_FOUR(BeatsPerMeasure.FOUR, BeatType.QUARTER),
  FIVE_FOUR(BeatsPerMeasure.FIVE, BeatType.QUARTER),
  SIX_FOUR(BeatsPerMeasure.SIX, BeatType.QUARTER),
  SEVEN_FOUR(BeatsPerMeasure.SEVEN, BeatType.QUARTER),
  EIGHT_FOUR(BeatsPerMeasure.EIGHT, BeatType.QUARTER),
  NINE_FOUR(BeatsPerMeasure.NINE, BeatType.QUARTER),

  ONE_TWO(BeatsPerMeasure.ONE, BeatType.HALF), TWO_TWO(BeatsPerMeasure.TWO, BeatType.HALF),
  THREE_TWO(BeatsPerMeasure.THREE, BeatType.HALF), FOUR_TWO(BeatsPerMeasure.FOUR, BeatType.HALF),
  FIVE_TWO(BeatsPerMeasure.FIVE, BeatType.HALF), SIX_TWO(BeatsPerMeasure.SIX, BeatType.HALF),
  SEVEN_TWO(BeatsPerMeasure.SEVEN, BeatType.HALF), EIGHT_TWO(BeatsPerMeasure.EIGHT, BeatType.HALF),
  NINE_TWO(BeatsPerMeasure.NINE, BeatType.HALF),

  ONE_ONE(BeatsPerMeasure.ONE, BeatType.WHOLE), TWO_ONE(BeatsPerMeasure.TWO, BeatType.WHOLE),
  THREE_ONE(BeatsPerMeasure.THREE, BeatType.WHOLE), FOUR_ONE(BeatsPerMeasure.FOUR, BeatType.WHOLE),
  FIVE_ONE(BeatsPerMeasure.FIVE, BeatType.WHOLE), SIX_ONE(BeatsPerMeasure.SIX, BeatType.WHOLE),
  SEVEN_ONE(BeatsPerMeasure.SEVEN, BeatType.WHOLE),
  EIGHT_ONE(BeatsPerMeasure.EIGHT, BeatType.WHOLE),
  NINE_ONE(BeatsPerMeasure.NINE, BeatType.WHOLE);

  private BeatsPerMeasure beatsPerMeasure;
  private BeatType beatType;

  /**
   * Constructs a Meter type storing a BeatPerMeasure and BeatType.
   *
   * @param bpm the BeatPerMeasure
   * @param bt  the BeatType
   */
  Meter(BeatsPerMeasure bpm, BeatType bt) {
    this.beatsPerMeasure = bpm;
    this.beatType = bt;
  }

  /**
   * Gets the BeatsPerMeasure.
   *
   * @return the BeatsPerMeasure
   */
  public BeatsPerMeasure getBeatsPerMeasure() {
    return this.beatsPerMeasure;
  }

  /**
   * Gets the BeatType.
   *
   * @return the BeatType
   */
  public BeatType getBeatType() {
    return this.beatType;
  }
}
