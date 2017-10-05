package cs3500.music.model;

import org.junit.Before;
import org.junit.Test;

import cs3500.music.util.CompositionBuilder;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for Music Editor.
 */
public class MusicEditorTest {


  private IMusicEditor musicEditorOne = new MusicEditorModel();
  private IMusicEditor musicEditorTwo = new MusicEditorModel();

  private Note aSharp4 = new Note(Pitch.AS, 4, 3, 4);
  private Note gNatural5 = new Note(Pitch.G, 5, 1, 2);
  private Note cNatural3 = new Note(Pitch.C, 3, 6, 8);

  @Before
  public void init() {
    musicEditorOne.addNote(aSharp4);
    musicEditorOne.addNote(gNatural5);
  }

  /**
   * Testing adding an note.
   */
  @Test
  public void addNote() {
    assertEquals(7, musicEditorOne.lengthOfMusic());
  }


  /**
   * Testing an empty editor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyEditor() {
    musicEditorTwo.musicRange();
  }


  /**
   * Testing removing an note.
   */
  @Test
  public void removeNote() {
    musicEditorOne.removeNote(aSharp4);
    assertEquals(3, musicEditorOne.lengthOfMusic());
  }

  /**
   * Testing merging two music Editors.
   */
  @Test
  public void mergeMusic() {
    musicEditorTwo.addNote(cNatural3);
    assertEquals(7, musicEditorOne.lengthOfMusic());
    musicEditorOne.mergeMusic(musicEditorTwo);
    assertEquals(14, musicEditorOne.lengthOfMusic());
  }

  /**
   * Testing appending one music to another.
   */
  @Test
  public void appendMusic() {
    musicEditorTwo.addNote(cNatural3);
    musicEditorOne.appendMusic(musicEditorTwo);
    assertEquals(21, musicEditorOne.lengthOfMusic());
  }

  /**
   * Testing the length of music.
   */
  @Test
  public void testLengthOfMusic() {
    assertEquals(7, musicEditorOne.lengthOfMusic());
    assertEquals(0, musicEditorTwo.lengthOfMusic());
    musicEditorTwo.addNote(gNatural5);
    assertEquals(3, musicEditorTwo.lengthOfMusic());
    musicEditorOne.mergeMusic(musicEditorTwo);
    assertEquals(7, musicEditorOne.lengthOfMusic());
    musicEditorOne.appendMusic(musicEditorTwo);
    assertEquals(10, musicEditorOne.lengthOfMusic());
  }

  /**
   * Testing musical notes.
   */
  @Test
  public void testMusicalNotes() {
    assertTrue(musicEditorOne.musicalValues().contains(gNatural5));
    assertFalse(musicEditorOne.musicalValues().contains(cNatural3));
    musicEditorTwo.addNote(cNatural3);
    musicEditorOne.appendMusic(musicEditorTwo);
    assertTrue(musicEditorOne.musicalValues().contains(cNatural3));

  }


  /**
   * Testing if two distinct notes of different times can be played together.
   */
  @Test
  public void testChords() {
    musicEditorTwo.addNote(cNatural3);
    assertEquals(0, musicEditorTwo.musicRange());
    Note fNatural3 = new Note(Pitch.F, 3, 6, 8);
    musicEditorTwo.addNote(fNatural3);
    assertEquals(5, musicEditorTwo.musicRange());
  }

  /**
   * Testing whether the bulder builds a Music editor and adds the correct notes.
   */
  @Test
  public void testBuilder() {
    CompositionBuilder<IMusicEditor> builder = new MusicEditorModel.Builder();
    builder.addNote(6, 14, 0, 40, 0);
    builder.addNote(6, 14, 0, 45, 0);
    IMusicEditor model = builder.build();
    assertEquals(14, model.lengthOfMusic());
    assertEquals(5, model.musicRange());
  }

  /**
   * Testing whether the builder sends the right exception when an invalid tempo is used.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWrongTempo() {
    CompositionBuilder<IMusicEditor> builder = new MusicEditorModel.Builder();
    builder.setTempo(-4);
  }
}