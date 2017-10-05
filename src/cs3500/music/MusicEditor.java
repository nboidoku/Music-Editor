package cs3500.music;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


import cs3500.music.controller.Controller;
import cs3500.music.controller.GUIController;
import cs3500.music.controller.IController;
import cs3500.music.model.IMusicEditor;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.CompositeView;
import cs3500.music.view.GuiView;
import cs3500.music.view.ConsoleView;

import cs3500.music.view.IView;
import cs3500.music.view.IViewModel;
import cs3500.music.view.MusicView;

import cs3500.music.view.ViewFactory;
import cs3500.music.view.ViewModel;

/**
 * The main class.
 */
public final class MusicEditor {


  /**
   * The main method.
   *
   * @param args the command line args to be specified.
   */
  public static void main(String args[]) {

    CompositionBuilder<IMusicEditor> builder = new MusicEditorModel.Builder();

    File file = new File(args[0]);
    Readable read = null;
    try {
      read = new FileReader(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    IMusicEditor model = MusicReader.parseFile(read, builder);

    IViewModel viewModel = new ViewModel(model);

    if (args[1].equals("composite")) {
      GuiView view = new CompositeView(viewModel);
      GUIController controller = new GUIController(view, model);
      controller.execute();
    }

    if (args[1].equals("gui")) {
      GuiView view = new MusicView(viewModel);
      GUIController controller = new GUIController(view, model);
      controller.execute();
    }

    if (args[1].equals("console")) {
      IView view = ViewFactory.createView("console", viewModel);
      IController controller = new Controller(view);
      controller.execute();

    }

  }
}

