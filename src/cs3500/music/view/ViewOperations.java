package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * The operations that all of my views need to be able to do.
 */
public interface ViewOperations {

  /**
   * Renders the view, whatever the specifics of that rendering may be.
   */
  void render();

}

//  // THIS IS ALL STUFF THAT WOULD BE NICE OR IS A FOSSIL FROM THE MVC EXAMPLE
//
//  /**
//   * Sets the tempo to some bpm (only accepts positive non-zero numbers with some upper limit).
//   */
//  void setTempo();
//
//  /**
//   * Set the label that is showing what the model stores.
//   */
//  void setEchoOutput(String s);
//
//  /**
//   * Get the string from the ext field and return it.
//   * @return the String from the text field
//   */
//  String getInputString();
//
//
//  /**
//   * Clears the text field. (not set input string because the user sets it with input).
//   */
//  void clearInputString();
//
//  /**
//   * Resets the focus back to the frame.
//   */
//  void resetFocus();
//
//  /**
//   * Toggle the color of the displayed text. Explicitly in the view's domain.
//   */
//  void toggleColor();
//
//  /**
//   * Forces the view to have a method to set up the (typing) keyboard.
//   * Same method signature to add a KeyListener to Swing.
//   *
//   * @param listener the listener to be added
//   */
//  void addKeyListener(KeyListener listener);
//
//
//  /**
//   * Forces the view to have a method to set up actions for buttons.
//   * All buttons must be given this action listener.
//   *
//   * @param listener the listener to be added
//   */
//  void addActionListener(ActionListener listener);
//
//  // Optional ones that might be fun
//  /**
//   * Replaces the song title with the given String.
//   * @param newTitle the new title to be set
//   */
//  void setSongTitle(String newTitle);
