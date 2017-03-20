package cs3500.music;

import cs3500.music.view.ViewFactory;
import cs3500.music.view.ViewOperations;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {

    ViewOperations visualView = ViewFactory.create("visual");
    ViewOperations midiView = ViewFactory.create("midi");
    ViewOperations textView = ViewFactory.create("console");

    // You probably need to connect these views to your model, too...


  }
}
