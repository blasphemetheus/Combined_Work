package cs3500.music.view;

import cs3500.music.controller.ButtonListener;
import cs3500.music.controller.KeyboardListener;

/**
 * The operations that all of my views need to be able to do.
 */
public interface ViewOperations {

  /**
   * Renders the view, whatever the specifics of that rendering may be.
   */
  void render();


    /**
   * Forces the view to have a method to set up the (typing) keyboard.
   * Same method signature to add a KeyListener to Swing.
   *
   * @param kbd the listener to be added
   */
  void addKeyListener(KeyboardListener kbd);

  /**
   * Forces the view to have a method to set up actions for buttons.
   * All buttons must be given this action listener.
   *
   * @param buttonListener the listener to be added
   */
  void addActionListener(ButtonListener buttonListener);
}
