package cs3500.music.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import cs3500.music.model.IMusicEditor;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.util.CompositionBuilder;

import static org.junit.Assert.assertEquals;

/**
 * Test for the console view.
 */
public class ConsoleViewTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }

  @Test
  public void testConsoleOutput() {
    CompositionBuilder<IMusicEditor> builder = new MusicEditorModel.Builder();
    IMusicEditor model = builder.build();
    IViewModel viewModel = new ViewModel(model);
    IView view = ViewFactory.createView("Console", viewModel);
    view.play();
    assertEquals("Empty Editor\n", outContent.toString());
  }

  @Test
  public void testConsoleOutput2() {
    CompositionBuilder<IMusicEditor> builder = new MusicEditorModel.Builder();
    builder.addNote(6, 14, 0, 40, 0);
    IMusicEditor model = builder.build();
    IViewModel viewModel = new ViewModel(model);
    IView view = ViewFactory.createView("Console", viewModel);
    view.play();
    assertEquals("    E3 \n" +
            " 0    \n" +
            " 1    \n" +
            " 2    \n" +
            " 3    \n" +
            " 4    \n" +
            " 5    \n" +
            " 6  X \n" +
            " 7  | \n" +
            " 8  | \n" +
            " 9  | \n" +
            "10  | \n" +
            "11  | \n" +
            "12  | \n" +
            "13  | " ,outContent.toString());
  }

  @Test
  public void testConsoleOutput3() {
    CompositionBuilder<IMusicEditor> builder = new MusicEditorModel.Builder();
    builder.addNote(6, 14, 0, 40, 0);
    builder.addNote(6, 14, 0, 41, 0);
    IMusicEditor model = builder.build();
    IViewModel viewModel = new ViewModel(model);
    IView view = ViewFactory.createView("Console", viewModel);
    view.play();
    assertEquals("    E3   F3 \n" +
            " 0         \n" +
            " 1         \n" +
            " 2         \n" +
            " 3         \n" +
            " 4         \n" +
            " 5         \n" +
            " 6  X    X \n" +
            " 7  |    | \n" +
            " 8  |    | \n" +
            " 9  |    | \n" +
            "10  |    | \n" +
            "11  |    | \n" +
            "12  |    | \n" +
            "13  |    | ", outContent.toString());
  }

  @Test
  public void testConsoleOutput4() {
    CompositionBuilder<IMusicEditor> builder = new MusicEditorModel.Builder();
    builder.addNote(6, 14, 0, 40, 0);
    builder.addNote(6, 14, 0, 41, 0);
    builder.addNote(10, 20, 0, 45, 0);
    IMusicEditor model = builder.build();
    IViewModel viewModel = new ViewModel(model);
    IView view = ViewFactory.createView("Console", viewModel);
    view.play();
    assertEquals("    E3   F3  F#3   G3  G#3   A3 \n" +
            " 0                             \n" +
            " 1                             \n" +
            " 2                             \n" +
            " 3                             \n" +
            " 4                             \n" +
            " 5                             \n" +
            " 6  X    X                     \n" +
            " 7  |    |                     \n" +
            " 8  |    |                     \n" +
            " 9  |    |                     \n" +
            "10  |    |                   X \n" +
            "11  |    |                   | \n" +
            "12  |    |                   | \n" +
            "13  |    |                   | \n" +
            "14                           | \n" +
            "15                           | \n" +
            "16                           | \n" +
            "17                           | \n" +
            "18                           | \n" +
            "19                           | ", outContent.toString());
  }

  @Test
  public void testConsoleOutput5() {
    CompositionBuilder<IMusicEditor> builder = new MusicEditorModel.Builder();
    builder.addNote(6, 14, 0, 40, 0);
    builder.addNote(6, 14, 0, 41, 0);
    builder.addNote(8, 14, 0, 40, 0);
    builder.addNote(10, 20, 0, 45, 0);
    IMusicEditor model = builder.build();
    ViewModel viewModel = new ViewModel(model);
    IView view = ViewFactory.createView("Console", viewModel);
    view.play();
    assertEquals("    E3   F3  F#3   G3  G#3   A3 \n" +
            " 0                             \n" +
            " 1                             \n" +
            " 2                             \n" +
            " 3                             \n" +
            " 4                             \n" +
            " 5                             \n" +
            " 6  X    X                     \n" +
            " 7  |    |                     \n" +
            " 8  X    |                     \n" +
            " 9  |    |                     \n" +
            "10  |    |                   X \n" +
            "11  |    |                   | \n" +
            "12  |    |                   | \n" +
            "13  |    |                   | \n" +
            "14                           | \n" +
            "15                           | \n" +
            "16                           | \n" +
            "17                           | \n" +
            "18                           | \n" +
            "19                           | ", outContent.toString());
  }
}