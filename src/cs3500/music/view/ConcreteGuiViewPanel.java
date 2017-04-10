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
  public int BEAT;

  public ConcreteGuiViewPanel(MusicModel model) {
    this.model = model;
  }
  public final int NOTE_HEIGHT = 10;
  public final int NOTE_WIDTH = 20;
  public final int PIANO_WIDTH = 20;
  public final int PIANO_HEIGHT = 250;

  public java.util.List<Note> firstline = new ArrayList<>();
  public java.util.List<Integer> beats = new ArrayList<>();
  public java.util.List<Note> allNotes = new ArrayList<>();

  @Override
  public void paintComponent(Graphics g) {
    // Handle the default painting
    super.paintComponent(g);
    g.clearRect(0,0,WIDTH,HEIGHT);
    // Look for more documentation about the Graphics class,
    // and methods on it that may be useful
    makeBeatText(g);
    makeRedLine(g);
    makeNotes(g);
    makeNoteGrid(g);
    makePiano(g);
    updateBeat();
    makeNoteText(g);
  }

  /**
   * Draw the piano tiles: make a white tile if it is flat otherwise make a black one for sharp
   * @param g
   */
  public void makePiano(Graphics g) {

    ///make all possible combination of notes for piano keys
    for (int i = 1; i <= 10; i++) {
      for (int j = 1; j <= columns(i, 12); j++) {
        // firstline.add(new Note(new Pitch(j, null), new Octave(i), null));
        allNotes.add(new Note(Pitch.getPint(j), Octave.fromInt(i), null));
      }
    }
    for(int k = 0; k < allNotes.size(); k++) {
      if (!(allNotes.get(k).getPitch().toString().contains("#"))) {
        g.drawRect(k * PIANO_WIDTH,(NOTE_WIDTH/2 + NOTE_WIDTH) + NOTE_WIDTH * firstline
                        .size(),
                PIANO_WIDTH ,
                PIANO_HEIGHT);
      } else {
        if (allNotes.get(k).getPitch().toString().contains("#")) {
          g.fillRect(k * PIANO_WIDTH, (NOTE_WIDTH / 2 + NOTE_WIDTH) +
                  NOTE_WIDTH * firstline.size(), PIANO_WIDTH / 2, PIANO_HEIGHT / 2);
          allNotes.remove(k);
          k--;
        }
      }
    }
  }

  /**
   * Draws a note onto the grid depending on the state of the note. A note which is not being
   * played wil be blank. A note that is in its onset will be black. A not being played but not
   * in its onset will be green.
   * @param g
   */
  public void makeNotes(Graphics g) {
    int duration;
    for (Note note : model.getNotes()) {
      duration = note.getDur().getEndBeat() - note.getBeatStart();
      g.setColor(Color.GREEN);
      g.fillRect(note.getBeatStart() * NOTE_WIDTH + (2 * NOTE_WIDTH),
              makeY(note),
               //(Collections.max(beats) - note.getPitch().getOrder()),
              NOTE_WIDTH * (duration),
              NOTE_HEIGHT * 2);
      g.setColor(Color.BLACK);
      g.fillRect(note.getBeatStart() * NOTE_WIDTH + (2 * NOTE_WIDTH),
              //(Collections.max(beats) - note.getPitch().getOrder()),
              makeY(note),
              NOTE_WIDTH,
              NOTE_HEIGHT * 2);
    }
  }

  /**
   * Sets the Y posns of the notes according to their Notes.
   *
   * @param note
   * @return Y posn for notes
   */
  public int makeY(Note note) {
    int k = 0;
    for(int i=0;i<firstline.size();i++) {
      if (note.getPitch().getOrder() == firstline.get(i).getPitch().getOrder()) {
        k = (3 * NOTE_HEIGHT - NOTE_HEIGHT/2) +
                NOTE_WIDTH * i;
      }
    }
    return k;
  }

  /**
   * Draw the grid that will hold all the notes. Every change in octave will be indicated by a
   * bolder lone
   * @param g
   */
  public void makeNoteGrid(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g.setColor(Color.black);
    g2.setStroke(new BasicStroke(2));
    for (int i = 0; i < firstline.size(); i++) {
      for (int j = 0; j <= Collections.max(beats)/4; j++) {
        g.drawRect(j * (4 * NOTE_WIDTH) + (NOTE_HEIGHT * 4), (3 * NOTE_HEIGHT - NOTE_HEIGHT/2) +
                        NOTE_WIDTH * i,
                NOTE_WIDTH * 4,
                NOTE_HEIGHT * 2);
      }
    }
  }

  /**
   * Draw the red line that tracks the beat being played. THe redline only exists within the grid
   * that holds all the notes.
   * @param g
   */
  public void makeRedLine(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.RED);
    g2.setStroke(new BasicStroke(4));
    g2.drawLine(NOTE_HEIGHT * 4 + (NOTE_WIDTH * BEAT),2 * NOTE_WIDTH - 5 ,NOTE_HEIGHT * 4 +
                    (NOTE_WIDTH * BEAT),
            firstline.size() *
            NOTE_WIDTH + NOTE_WIDTH);
  }

  public void updateBeat() {

    BEAT++;

    repaint();
  }

  /**
   * Draws the beats in increments of four only to end at the last possible beat(max beat)
   * @param g
   */
  public void makeBeatText(Graphics g) {
    ///Extract the end beats of our notes and store them
    for (int i = 0; i < model.getNotes().size(); i++) {
      java.util.List<Note> lon = model.getNotes();
      Duration dur = lon.get(i).getDur();
      int endBeat = dur.getEndBeat();
      beats.add(endBeat);
    }
    ///Get the last beat
    int beatMax = Collections.max(beats);

    for(int i = 0; i <= beatMax; i += 4) {
      g.drawString(i + "", (i * NOTE_WIDTH) + (NOTE_HEIGHT * 4) ,NOTE_WIDTH);
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
      for (int i = 0; i < firstline.size(); i++) {
        g.drawString(firstline.get(i).toString(), 0,
                (2 * NOTE_WIDTH) + NOTE_WIDTH * i);
      }
  }

  /// added twice because of some import error
  /// see MusicModel for javadoc
  public int columns(int i, int max) {
    if (i == max) {
      return max;
    } else {
      return 11;
    }
  }
}
