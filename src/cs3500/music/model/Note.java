package cs3500.music.model;


import static java.util.Objects.hash;

/**
 * Class to represent a note.
 */
public class Note {
  public final int volume;
  public final int instrument;
  private int pitchOctave;
  private int startBeat;
  private int duration;

  /**
   * Constructor for note.
   * @param pitch the pitch
   * @param octave the octave
   * @param startBeat the startBeat
   * @param duration the duration of the note
   * @param volume the volume of the note
   * @param instrument the instrument of the note
   */
  public Note(Pitch pitch, int octave, int startBeat, int duration, int volume, int instrument) {
    this.pitchOctave = pitch.getValue() + (12 * octave);
    if (startBeat < 0) {
      throw new IllegalArgumentException("Starting beat has to be positive");
    } else {
      this.startBeat = startBeat;
    }
    if (duration >= 0) {
      this.duration = duration;
    } else {
      throw new IllegalArgumentException("Not a valid beat.");
    }
    this.volume = volume;
    this.instrument = instrument;
  }

  /**
   * A constructor without with default volume and instrument.
   * @param pitch the pitch
   * @param octave the octave
   * @param startBeat the startBeat
   * @param duration the duration of the note
   */
  public Note(Pitch pitch, int octave, int startBeat, int duration) {
    this.pitchOctave = pitch.getValue() + (12 * octave);
    if (startBeat < 0) {
      throw new IllegalArgumentException("Starting beat has to be positive");
    } else {
      this.startBeat = startBeat;
    }
    if (duration >= 0) {
      this.duration = duration;
    } else {
      throw new IllegalArgumentException("Not a valid beat.");

    }
    this.volume = 0;
    this.instrument = 0;
  }


  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Note)) {
      return false;
    } else {
      Note another = (Note) other;
      return this.pitchOctave == another.pitchOctave && this.startBeat ==
              another.startBeat && this.duration == another.duration;
    }
  }

  @Override
  public int hashCode() {
    return hash(pitchOctave, startBeat, duration);
  }

  public int endsAtBeat() {
    return this.startBeat + this.duration;
  }

  @Override
  public String toString() {
    return Pitch.toString(this.getPitchOctave() % 12) + this.pitchOctave / 12;
  }

  /**
   * Getter to return the pitchOctave of a note.
   *
   * @return this pitchOctave
   */
  public int getPitchOctave() {
    return this.pitchOctave;
  }

  /**
   * Adds a particular amount to the start time of this note.
   *
   * @param offset the amount to add
   */
  public void addToStartTime(int offset) {
    this.startBeat = this.startBeat + offset;
  }

  /**
   * Checks if this note is greater than that note.
   *
   * @param other the other note
   * @return true if this note is greate
   */
  boolean isGreaterThan(Note other) {
    return this.pitchOctave > other.pitchOctave;
  }

  /**
   * Gets the range of values of notes with respect to this note.
   *
   * @param highest the highest value to be compared to
   * @return the range
   */
  int getRange(Note highest) {
    return highest.pitchOctave - this.pitchOctave;
  }

  /**
   * Getter for this startBeat.
   *
   * @return the startBeat
   */
  public int getStartBeat() {
    return this.startBeat;
  }

}
