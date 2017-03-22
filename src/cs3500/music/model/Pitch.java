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
   * Getter for the order
   */

  public int getOrder() {
    return this.order;
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
   * Get the pitch that has the folling int
   *
   * @param x   the pitch int to be extracted
   * @return the correct pitch coressponding to that number
   */
   public static Pitch getPint(int x){
    switch (x) {
      case 0:
        return A$B;
      case 1:
        return B;
      case 2:
        return C;
      case 3:
        return C$D;
      case 4:
        return D;
      case 5:
        return D$E;
      case 6:
        return E;
      case 7:
        return F;
      case 8:
        return F$G;
      case 9:
        return G;
      case 10:
        return G$A;
      case 11:
        return A;
      default:
        throw new IllegalArgumentException("Out of bounds integer: " + x);
    }
  }
}
