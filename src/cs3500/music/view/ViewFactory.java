package cs3500.music.view;

/**
 * A factory of views.
 */
public class ViewFactory {

  /**
   * Creates a view.
   *
   * @param view      a String of the intended view.
   * @param viewModel the viewModel to be used.
   */
  public static IView createView(String view, IViewModel viewModel) {
    IView result;
    switch (view.toLowerCase()) {
      case "console":
        result = new ConsoleView(viewModel);
        break;
      case "gui":
        result = new MusicView(viewModel);
        break;
      case "midi":
        result = new MidiView(viewModel);
        break;
      /*case "composite":
        result = new CompositeView(new MusicView(viewModel),
                new MidiView(viewModel));*/
      default:
        throw new IllegalArgumentException("No such view exists");
    }
    return result;
  }
}
