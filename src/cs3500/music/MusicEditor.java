package cs3500.music;

import cs3500.music.controller.ControllerOperations;
import cs3500.music.controller.MusicController;
import cs3500.music.model.ModelOperations;
import cs3500.music.model.MusicModel;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ViewFactory;
import cs3500.music.view.ViewOperations;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

/**
 * The class that holds the main method, represents the outer shell of the Music Editor.
 */
public class MusicEditor {

  /**
   * In a MVC fashion renders a view to the client, stores information in the model and allows a
   * controller to direct things (on actions/buttons do things) for the editor. The String[] args
   * passed in allow us to read in files as well as help determine the view output type
   * (ie console = text to console, midi = audio playback, visual = gui interface).
   *
   * @param args a file to be read in's name (ie "example.txt"), and then the specified view
   * @throws IOException
   * @throws InvalidMidiDataException
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    // default value for no specified file is ""
    String fileName = "";
    // default value for if no arguments are specified is "visual"
    String typeOfView = "visual";

    ModelOperations model;
    ViewOperations view;
    ControllerOperations controller;


    // checks that a valid number of args were passed in
    if (args.length == 0) {
      // default if no arguments are specified is to show the visual view
      typeOfView = "visual";
      System.out.println(typeOfView + ":typeofview");
      System.out.println(fileName + "--nothing else on this line right?");
    } else {
      if (args.length == 2) {
        fileName += args[0];
        typeOfView = args[1];
        System.out.println(typeOfView + ":typeofview");
        System.out.println(fileName + ":File");
      } else {
        throw new IOException("Invalid number of inputs in args, should be two: "
                + args.toString());
      }
    }


    //

    // Readables contain

    // useFile is true if we passed in a file name to be used, else false
    boolean useFile;
    if (fileName.equals("")) {
      useFile = false;
    } else {
      useFile = true;
    }

    //TODO figure out how to specify this
    //TODO I think we'll have to change this for each computer/storage location for the files
    String path = "storage path for txt files";
    Readable fileReader = new FileReader(path + fileName);

    // if reading from file then make model from file, else make an entirely new model
    if (useFile) {
      model = MusicReader.parseFile(fileReader, new MusicModel.Builder());
    } else {
      model = new MusicModel();
    }

    view = ViewFactory.create(typeOfView, model);

    controller = new MusicController(model, view);

    controller.start();

//    ViewOperations visualView = ViewFactory.create("visual");
//    ViewOperations midiView = ViewFactory.create("midi");
//    ViewOperations textView = ViewFactory.create("console");
  }
}
