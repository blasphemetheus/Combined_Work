package cs3500.music.run;

import javax.swing.text.View;

import cs3500.music.controller.ControllerOperations;
import cs3500.music.controller.MusicController;
import cs3500.music.model.ModelOperations;
import cs3500.music.model.MusicModel;

/**
 * Created by blewf on 3/18/2017.
 */
public class MusicEditor {
  public static void main(String[] args) {
    ModelOperations model = new MusicModel();
    ViewOperations view = new MusicView("Echo some String?");
    ControllerOperations controller = new MusicController(model, view);


  }
}
