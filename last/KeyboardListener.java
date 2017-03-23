package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * Represents a keyboard listener. Configurable by the Controller which instantiates.
 * Keeps three maps, one for key typed, pressed and released. Each map stores a key mapping,
 * which is a pair (keystroke and then code to execute with that stroke). The second
 * part of the pair is a function object (object of a class that implements Runnable - one void
 * method).
 */
public class KeyboardListener implements KeyListener {
  private Map<Character, Runnable> keyTypedMap;
  private Map<Integer, Runnable> keyPressedMap;
  private Map<Integer, Runnable> keyReleasedMap;

  /**
   * Sets the map for keytyped events. In Java Swing these are characters.
   *
   * @param map the map to be set
   */
  public void setKeyTypedMap(Map<Character, Runnable> map) {
    keyTypedMap = map;
  }

  /**
   * Sets the map for keypressed events. In Java Swing these are Integer codes.
   *
   * @param map the map to be set
   */
  public void setKeyPressedMap(Map<Integer, Runnable> map) {
    keyPressedMap = map;
  }

  /**
   * Sets the map for keyreleased events. In Java Swing thes are Integer codes.
   *
   * @param map the map to be set
   */
  public void setKeyReleasedMap(Map<Integer, Runnable> map) {
    keyReleasedMap = map;
  }


  /**
   * This happens when the view registers a key as being typed. The method finds if
   * anything has been mapped to this keyEvent character and if so, executes it.
   *
   * @param e the KeyEvent
   */
  @Override
  public void keyTyped(KeyEvent e) {
    if (keyTypedMap.containsKey(e.getKeyChar())) {
      keyTypedMap.get(e.getKeyChar()).run();
    }

  }

  /**
   * This happens when the view registers a key as being pressed. The method finds if
   * anything has been mapped to this keyEvent character and if so, executes it.
   *
   * @param e the KeyEvent
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if (keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  /**
   * This happens when the view registers a key as being released. The method finds if
   * anything has been mapped to this keyEvent character and if so, executes it.
   *
   * @param e the KeyEvent
   */
  @Override
  public void keyReleased(KeyEvent e) {
    if (keyReleasedMap.containsKey(e.getKeyCode())) {
      keyReleasedMap.get(e.getKeyCode()).run();
    }
  }

}
