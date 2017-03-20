package cs3500.music.view;

/**
 * A factory of views, constructs an appropriate view based on the String input.
 */
public final class ViewFactory {

  public static final ViewOperations create(String typeOfView) {
    switch (typeOfView) {
      case "visual" :
        return new VisualView();
      case "midi" :
        return new MidiView();
      case "console" :
        return new TextualView();
      default :
        throw new IllegalArgumentException("Incorrect String, cannot make that type of view: "
                + typeOfView);
    }
  }
}
