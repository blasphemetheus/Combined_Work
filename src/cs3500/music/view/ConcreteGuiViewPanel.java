package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

import cs3500.music.model.Duration;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Octave;
import cs3500.music.model.Pitch;

/**
 * A dummy view that simply draws a string 
 */
public class ConcreteGuiViewPanel extends JPanel {

  private MusicModel model;

  public ConcreteGuiViewPanel(MusicModel model) {
    this.model = model;
  }
  public final int NOTE_WIDTH = 10;
  public final int NOTE_HEIGHT = 20;


  @Override
  public void paintComponent(Graphics g){
    // Handle the default painting
    super.paintComponent(g);
    g.clearRect(0,0,WIDTH,HEIGHT);
    // Look for more documentation about the Graphics class,
    // and methods on it that may be useful
    makeNoteText(g);
    makebeatText(g);
  }

  /**
   * Draws the beats in increments of four only to end at the last possible beat(max beat)
   * @param g
   */
  public void makebeatText(Graphics g) {
    ///Extract the end beats of our notes and store them
    java.util.List<Integer> beats = new ArrayList<>();
    for (int i = 0; i < model.getNotes().size(); i++) {
      java.util.List<Note> lon = model.getNotes();
      Duration dur = lon.get(i).getDur();
      int endBeat = dur.getEndBeat();
      beats.add(endBeat);
    }
    ///Get the last beat
    int beatMax = Collections.max(beats);

    for(int i = 0; i <= beatMax; i += 4) {
      g.drawString(i + "", (i * NOTE_WIDTH * 4) + (NOTE_WIDTH * 4) ,NOTE_HEIGHT);
    }
  }

  /**
   * Draws all the Pitches and Octaves that are within the highest and lowest note on the left side
   * of the panel.
   * @param g
   */
  public void makeNoteText(Graphics g) {
    ///Accumulate pitches and octaves to empty lists to determine min and max values
    java.util.List<Integer> firstline1 = new ArrayList<>();
    java.util.List<Integer> firstline2 = new ArrayList<>();
    java.util.List<Note> firstline = new ArrayList<>();
    for (int i = 0; i < model.getNotes().size(); i++) {
      firstline1.add(model.getNotes().get(i).getPitch().getOrder());
    }
    for (int i = 0; i < model.getNotes().size(); i++) {
      firstline2.add(model.getNotes().get(i).getOctave().toInt());
    }

    ///Sort out maximums and minimums
    int pitchMin = Collections.min(firstline1);
    int pitchMax = Collections.max(firstline1);
    int octaveMin = Collections.min(firstline2);
    int octaveMax = Collections.max(firstline2);

    ///make all possible combination between min and max values and add
    ///to an empty list
    for (int i = octaveMin; i <= octaveMax; i++) {
      for (int j = pitchMin; j <= columns(i, pitchMax); j++) {
        // firstline.add(new Note(new Pitch(j, null), new Octave(i), null));
        firstline.add(new Note(Pitch.getPint(j), Octave.fromInt(i), null));

      }
    }
    System.out.print(firstline);
    for(int i = 0; i < firstline.size(); i++) {
      g.drawString(firstline.get(i).toString(), 0,
              (2 * NOTE_HEIGHT) + NOTE_HEIGHT * i);
    }
  }


  /// added twice because of some import error
  public int columns(int i, int max) {
    if (i == max) {
      return max;
    } else {
      return 11;
    }
  }

}
