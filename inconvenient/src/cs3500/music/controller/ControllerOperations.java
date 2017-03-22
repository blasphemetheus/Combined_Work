package cs3500.music.controller;

import java.awt.event.ActionListener;

/**
 * Represents the public facing interface for my Music Controller, extends ActionListener.
 */
public interface ControllerOperations {

  /**
   * Begins the interactive part of the editor.
   * (ie rendering the view, listening, interacting with the model).
   */
  void start();
}
