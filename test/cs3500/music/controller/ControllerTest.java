package cs3500.music.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import cs3500.music.model.IMusicEditor;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.view.IView;
import cs3500.music.view.IViewModel;
import cs3500.music.view.ViewFactory;
import cs3500.music.view.ViewModel;

import static org.junit.Assert.assertEquals;

/**
 * Test for the Controller.
 */
public class ControllerTest {

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
    Controller controller = new Controller(view);
    controller.execute();
    assertEquals("Empty Editor\n", outContent.toString());
  }

}