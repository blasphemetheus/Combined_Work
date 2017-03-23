package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.model.ModelOperations;
import cs3500.music.view.ViewOperations;

/**
 * Our Controller for the Music Model, stores model and view and uses keyboard and button listeners.
 */
public class MusicController implements ControllerOperations {
  private ViewOperations view;


  /**
   * The public constructor, where we pass in the model and view and configure our listeners.
   *
   * @param m the ModelOperations
   * @param v the ViewOperations
   */
  public MusicController(ModelOperations m, ViewOperations v) {
    ModelOperations model;
    this.model = m;
    this.view = v;
    configureKeyBoardListener();
    configureButtonListener();
  }

  @Override
  public void start() {
    this.view.render();
  }


  //
  //  @Override
  //  public void actionPerformed(ActionEvent e) {
  //    switch (e.getActionCommand()) {
  //      // read from the input textfield
  //      case "Echo Button":
  //        String text = view.getInputString();
  //        // send text to the model
  //        model.setString(text);
  //
  //        //clear input text field
  //        view.clearInputString();
  //        // echo the string in view
  //        text = model.getString();
  //        view.setEchoOutput(text);
  //
  //        //set focus back to main frame so keyboard
  //        view.resetFocus();
  //        break;
  //      case "Exit Button":
  //        System.exit(0);
  //        break;
  //    }
  //  }


  private void configureKeyBoardListener() {
    Map<Character, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();

    // My Stuff
    keyTypes.put('c', new MoveBeatOneBackward());
    keyTypes.put('d', () -> this.view.printStuff("WOWZA"));

    keyPresses.put(KeyEvent.VK_LEFT, new MoveBeatOneBackward());
    keyPresses.put(KeyEvent.VK_RIGHT, new MoveBeatOneForward());

    // FOSSILS :
    //    keyPresses.put(KeyEvent.VK_C, new MakeCaps());
    //    keyReleases.put(KeyEvent.VK_C, new MakeOriginalCase());
    //    // anonymous class (that implements Runnable. We define the one required run method
    //    keyTypes.put('r', new Runnable() {
    //      public void run() {
    //        view.toggleColor();
    //      }
    //    });

    KeyboardListener kbd = new KeyboardListener();
    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    view.addKeyListener(kbd);
  }

  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();

    //    buttonClickedMap.put("Echo Button", new EchoButtonAction());
    // buttonClickedMap.put("Exit Button", new ExitButtonAction());

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }

  //-----------------------------------------------------------------

  // CLASSES NESTED INSIDE CONTROLLER SO THEY HAVE ACCESS TO THE VIEW
  //  class MakeOriginalCase implements Runnable {
  //    public void run() {
  //      String text = model.toString();
  //      view.setEchoOutput(text);
  //    }
  //  }

  class MoveBeatOneForward implements Runnable {
    public void run() {
      // moves beat of red line one forward
      // if in last beat moves to very end
      System.out.println("right");

    }
  }

  class MoveBeatOneBackward implements Runnable {
    public void run() {
      // moves beat of red line one backward
      // if at first beat, doesn't do anything (or restarts the beat)
      System.out.println("left");
    }
  }

  //  class MakeCaps implements Runnable {
  //    public void run() {
  //      String text = model.toString();
  //      text = text.toUpperCase();
  //      view.setEchoOutput(text);
  //    }
  //  }

  //  class EchoButtonAction implements Runnable {
  //    public void run() {
  //      String text = view.getInputString();
  //      // send text to the model
  //      model.setString(text);
  //
  //      //clear input text field
  //      view.clearInputString();
  //      // echo the string in view
  //      text = model.getString();
  //      view.setEchoOutput(text);
  //
  //      //set focus bck to main frame (for keyboard listerner stuff
  //      view.resetFocus();
  //    }
  //  }

  //  class ExitButtonAction implements Runnable {
  //    public void run() {
  //      System.exit(0);
  //    }
  //  }
}
