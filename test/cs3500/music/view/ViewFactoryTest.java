package cs3500.music.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import cs3500.music.model.IMusicEditor;
import cs3500.music.model.MusicEditorModel;

import static org.junit.Assert.assertEquals;

/**
 * Test for the View Factory.
 */
public class ViewFactoryTest {

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


  @Test(expected = IllegalArgumentException.class)
  public void testWrongFactory() {
    IMusicEditor model = new MusicEditorModel();
    IViewModel viewModel = new ViewModel(model);
    ViewFactory.createView("hello", viewModel);
  }

  @Test
  public void testConsoleFactory() {
    IMusicEditor model = new MusicEditorModel();
    IViewModel viewModel = new ViewModel(model);
    IView myView = ViewFactory.createView("Console", viewModel);
    myView.play();
    assertEquals("Empty Editor\n", outContent.toString());
  }

}

