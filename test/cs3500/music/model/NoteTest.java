package cs3500.music.model;

import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for the Note class.
 */
public class NoteTest {


  private IMusicEditor musicEditorOne = new MusicEditorModel();

  private Note aSharp4 = new Note(Pitch.AS, 4, 3, 4, 0, 0);
  private Note gNatural5 = new Note(Pitch.G, 5, 1, 2, 0, 0);

  @Before
  public void init() {
    musicEditorOne.addNote(aSharp4);
    musicEditorOne.addNote(gNatural5);
  }

  /**
   * Testing equal method.
   */
  @Test
  public void testEqualWithValid() {
    Note a0 = new Note(Pitch.A, 0, 2, 2);
    Note a02 = new Note(Pitch.A, 0, 2, 2);
    assertTrue(a0.equals(a02));
  }

  /**
   * Testing invalid startBeats, should throw an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartBeat() {
    Note invalidStartBeat = new Note(Pitch.A, 0, -3, 2);
    invalidStartBeat.addToStartTime(7);
  }


  /**
   * Testing invalid durations. Should throw an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBeat() {
    try {
      new Note(Pitch.A, 0, 2, -3);
    } catch (IllegalArgumentException a) {
      try {
        new Note(Pitch.A, 0, 2, -6);
      } catch (IllegalArgumentException a2) {
        //If the code manages to get here and throw this exception, it means all the initialisation
        // attempts within the other try statements were invalid because they threw exceptions too.
        new Note(Pitch.A, 0, 2, -9);
      }
    }
  }

  /**
   * Testing add to StartTime.
   */
  @Test
  public void testAddToStartTime() {
    assertEquals(7, musicEditorOne.lengthOfMusic());
    aSharp4.addToStartTime(5);
    assertEquals(12, musicEditorOne.lengthOfMusic());
  }


}