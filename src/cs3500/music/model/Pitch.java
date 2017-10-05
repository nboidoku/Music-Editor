package cs3500.music.model;

/**
 * Class representing a pitch.
 */
public enum Pitch {


  C(0), CS(1), D(2), DS(3), E(4), F(5), FS(6), G(7), GS(8), A(9), AS(10), B(11);

  private final int value;


  Pitch(int value) {
    this.value = value;
  }

  int getValue() {
    return this.value;
  }


  /**
   * Converts the pitch of a pitchOctave into a String.
   *
   * @param intPitch the pitch of the pitchOctave
   * @return the String
   */
  public static String toString(int intPitch) {
    String result = "";
    switch (intPitch) {
      case 9:
        result = "  A";
        break;
      case 10:
        result = " A#";
        break;
      case 11:
        result = "  B";
        break;
      case 0:
        result = "  C";
        break;
      case 1:
        result = " C#";
        break;
      case 2:
        result = "  D";
        break;
      case 3:
        result = " D#";
        break;
      case 4:
        result = "  E";
        break;
      case 5:
        result = "  F";
        break;
      case 6:
        result = " F#";
        break;
      case 7:
        result = "  G";
        break;
      case 8:
        result = " G#";
        break;
      default:
        throw new IllegalArgumentException("Invalid intPitch");
    }
    return result;
  }


  /**
   * Converts an int into a pitch.
   * @param pitch the pitch in int format
   * @return the pitch
   */
  public static Pitch intToPitch(int pitch) {
    switch (pitch % 12) {
      case 0:
        return Pitch.C;
      case 1:
        return Pitch.CS;
      case 2:
        return Pitch.D;
      case 3:
        return Pitch.DS;
      case 4:
        return Pitch.E;
      case 5:
        return Pitch.F;
      case 6:
        return Pitch.FS;
      case 7:
        return Pitch.G;
      case 8:
        return Pitch.GS;
      case 9:
        return Pitch.A;
      case 10:
        return Pitch.AS;
      case 11:
        return Pitch.B;
      default:
        throw new IllegalArgumentException("Invalid Pitch");
    }
  }


}
