package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * The Button Listener class that allows us to deal with button presses, actions.
 */
public class ButtonListener implements ActionListener {
  private Map<String, Runnable> buttonClickedActions;


  /**
   * Empty default constructor.
   */
  public ButtonListener() {

  }


  /**
   * Set the map for key-typed events. (in Java Swing they're characters).
   * @param map the map passed in
   */
  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    buttonClickedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {
      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }


}
