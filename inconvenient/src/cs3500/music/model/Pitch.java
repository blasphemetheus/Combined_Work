package cs3500.music.model;

/**
 * An enumeration of the allowable pitches a Note can have under Western Music norms.
 */
public enum Pitch {

  /**
   * The A note (range).
   */
  A(1, NoteDesignation.NATURAL),

  /**
   * The A# or Bb note (range).
   */
  A$B(2, NoteDesignation.ACCIDENTAL),

  /**
   * The B note (range).
   */
  B(3, NoteDesignation.NATURAL),

  /**
   * The C note (range).
   */
  C(4, NoteDesignation.NATURAL),

  /**
   * The C# or Db note (range).
   */
  C$D(5, NoteDesignation.ACCIDENTAL),

  /**
   * The D note (range).
   */
  D(6, NoteDesignation.NATURAL),

  /**
   * The D# or Eb note (range).
   */
  D$E(7, NoteDesignation.ACCIDENTAL),

  /**
   * The E note (range).
   */
  E(8, NoteDesignation.NATURAL),

  /**
   * The F note (range).
   */
  F(9, NoteDesignation.NATURAL),

  /**
   * The F# or Gb note (range).
   */
  F$G(10, NoteDesignation.ACCIDENTAL),

  /**
   * The G note (range).
   */
  G(11, NoteDesignation.NATURAL),

  /**
   * The G# or Ab note (range).
   */
  G$A(12, NoteDesignation.ACCIDENTAL);

  /**
   * The stored type of note.
   */
  private NoteDesignation type;

  /**
   * The stored ordering of notes within the octave.
   */
  private int order;

  /**
   * Constructs a Pitch storing the given NoteDesignation.
   *
   * @param type the type of note
   */
  Pitch(int order, NoteDesignation type) {
    this.order = order;
    this.type = type;
  }

  /**
   * Returns the String representation of this Pitch (in form of letter then accidental if exists).
   *
   * @return String representation of the Pitch
   */
  public String toString() {
    switch (this.type) {
      case ACCIDENTAL:
        return this.getChar() + "#";
      case NATURAL:
        return this.getChar();
      default:
        throw new IllegalArgumentException("Invalid NoteDesignation: " + this.type);
    }
  }

  /**
   * Returns the Note name associated with this Pitch.
   * (always returns the Sharp value for accidentals)
   * (i.e. C$D returns "C", D returns "D")
   *
   * @return the Note letter associated with this pitch (accidentals are sharps here)
   * @throws IllegalArgumentException if given Pitch is invalid
   */
  private String getChar() {
    switch (this) {
      case A:
      case A$B:
        return "A";
      case B:
        return "B";
      case C:
      case C$D:
        return "C";
      case D:
      case D$E:
        return "D";
      case E:
        return "E";
      case F:
      case F$G:
        return "F";
      case G:
      case G$A:
        return "G";
      default:
        throw new IllegalArgumentException("Invalid Pitch: " + this);
    }
  }

  /**
   * Gets the ordering int of the specific enum.
   *
   * @return the ordering int
   */
  public int getOrderVal() {
    return this.order;
  }

  /**
   * Returns a Pitch one value higher in the cycle than the given pitch.
   *
   * @param pitch the given pitch
   * @return a pitch shifted one higher
   */
  public static Pitch oneHigher(Pitch pitch) {
    switch (pitch) {
      case A:
        return A$B;
      case A$B:
        return B;
      case B:
        return C;
      case C:
        return C$D;
      case C$D:
        return D;
      case D:
        return D$E;
      case D$E:
        return E;
      case E:
        return F;
      case F:
        return F$G;
      case F$G:
        return G;
      case G:
        return G$A;
      case G$A:
        return A;
      default:
        throw new IllegalArgumentException("Invalid Pitch: " + pitch);
    }
  }


  /**
   * Returns the Pitch representing the provided int value.
   *
   * @param number the provided int value
   * @return the Pitch specified
   * @throws IllegalArgumentException if the provided int value doesn't represent to a valid Pitch
   */
  public static Pitch fromInt(int number) {
    switch (number) {
      case 1:
        return A;
      case 2:
        return A$B;
      case 3:
        return B;
      case 4:
        return C;
      case 5:
        return C$D;
      case 6:
        return D;
      case 7:
        return D$E;
      case 8:
        return E;
      case 9:
        return F;
      case 10:
        return F$G;
      case 11:
        return G;
      case 12:
        return G$A;
      default:
        throw new IllegalArgumentException("Invalid Pitch - Given: " + number);
    }
  }

  /**
   * Computes the Pitch enum corresponding to the int passed in.
   * @param pitch the int passed in
   * @return the Pitch enum corresponding to the int
   */
  public static Pitch computePitch(int pitch) {
    if (pitch > 140) {
      throw new IllegalArgumentException("This pitch is too high to store in model: " + pitch);
    }

    if (pitch < 21) {
      throw new IllegalArgumentException("This pitch is too low to store in model: " + pitch);
    }

    // adjusted should spit out an int where A = 1, A$B = 2, B = 3, etc
    int adjusted = pitch - 20;
    while (adjusted > 12) {
      adjusted += -12;
    }

    return Pitch.fromInt(adjusted);

//      int adjusted = pitch - 20;
//
//
//        // 1 - 12 = octave 1
//        // 109 - 120 = octave 10
//
//        switch(pitch) {
//
//        Math.floorMod(adjusted, 12);
  }
}