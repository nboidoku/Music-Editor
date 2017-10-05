package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for the Pitch class.
 */
public class PitchTest {

  @Test
  public void testPitch() {
    assertEquals(Pitch.intToPitch(0), Pitch.C);
    assertEquals(Pitch.intToPitch(1), Pitch.CS);
    assertEquals(Pitch.intToPitch(2), Pitch.D);
    assertEquals(Pitch.intToPitch(3), Pitch.DS);
    assertEquals(Pitch.intToPitch(4), Pitch.E);
    assertEquals(Pitch.intToPitch(5), Pitch.F);
    assertEquals(Pitch.intToPitch(6), Pitch.FS);
    assertEquals(Pitch.intToPitch(7), Pitch.G);
    assertEquals(Pitch.intToPitch(8), Pitch.GS);
    assertEquals(Pitch.intToPitch(9), Pitch.A);
    assertEquals(Pitch.intToPitch(10), Pitch.AS);
    assertEquals(Pitch.intToPitch(11), Pitch.B);
  }

}