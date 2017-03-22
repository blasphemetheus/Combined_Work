package cs3500.music;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs3500.music.model.Duration;
import cs3500.music.model.ModelOperations;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Octave;
import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * My class for testing ModelOperations.
 */
public class ModelOperationsTest {
  private ModelOperations model;
  private Note oneBeatMiddleC;
  private Note fourBeatMiddleC;
  private Note twoBeatLowGSharpAtNineNineNine;
  private Note oneBeatMiddleCAtNineNineEight;
  private Note oneBeatLowGSharpAtZero;
  private Note oneBeatLowGSharpAtOne;
  private Note fourBeatLowMiddleCAtSeven;
  //  private Track defTrack;
  //  private Track nonDefTrack;
  //  private Meter fourFour;
  //  private Meter threeFour;
  //  private Meter oneTwo;
  //  private Meter threeTwo;
  private Note oneBeatLowGSharp;
  private Note eighteenBeatHighA;
  private ModelOperations schizophrenia;
  private Note fourBeatLowMiddleC;
  private ModelOperations longSong;
  private Note fourBeatMiddleF;
  private ModelOperations monotony;
  private ModelOperations thousandBeats;
  private ModelOperations nines;
  private ModelOperations oneBeatTrack;

  /**
   * The before, setup thing.
   * @throws Exception fun
   */
  @Before
  public void setUp() throws Exception {

    this.model = new MusicModel();
    this.oneBeatMiddleC = new Note(Pitch.C, Octave.FOUR, new Duration(0, 1));
    this.fourBeatMiddleC = new Note(Pitch.C, Octave.FOUR, new Duration(0, 4));
    this.oneBeatLowGSharp = new Note(Pitch.G$A, Octave.TWO, new Duration(7, 4));
    oneBeatLowGSharpAtZero = new Note(Pitch.G$A, Octave.TWO, new Duration(0, 1));
    this.eighteenBeatHighA = new Note(Pitch.A, Octave.SEVEN, new Duration(3, 18));
    fourBeatLowMiddleCAtSeven = new Note(Pitch.C, Octave.FOUR, new Duration(7, 4));
    this.fourBeatLowMiddleC = new Note(Pitch.C, Octave.FOUR, new Duration(0, 4));
    this.fourBeatMiddleF = new Note(Pitch.F, Octave.FOUR, new Duration(0, 4));
    twoBeatLowGSharpAtNineNineNine = new Note(Pitch.G$A, Octave.ONE, new Duration(999,
            2));
    oneBeatMiddleCAtNineNineEight = new Note(Pitch.C, Octave.FOUR, new Duration(998,
            1));
    oneBeatLowGSharpAtOne = new Note(Pitch.G$A, Octave.TWO, new Duration(1, 1));

    this.schizophrenia = new MusicModel();
    this.createSchizophrenia();

    this.longSong = new MusicModel();
    this.createLongSong();

    this.monotony = new MusicModel();
    this.createMonotony();


    this.thousandBeats = new MusicModel();
    this.thousandBeats.addNote(twoBeatLowGSharpAtNineNineNine);

    this.nines = new MusicModel();
    this.nines.addNote(oneBeatMiddleCAtNineNineEight);

    this.oneBeatTrack = new MusicModel();
    this.oneBeatTrack.addNote(this.oneBeatLowGSharpAtZero);
  }

  /**
   * Creates the schizophrenia Track.
   */
  private void createSchizophrenia() {
    Duration startAtZeroHoldOne = new Duration(0, 1);
    Duration startAtOneHoldOne = new Duration(1, 1);
    Duration startAtSevenHoldFour = new Duration(7, 4);
    Duration startAtThreeHoldEighteen = new Duration(3, 18);



    this.schizophrenia.addNote(fourBeatLowMiddleC);
    this.schizophrenia.addNote(oneBeatLowGSharpAtOne);
    this.schizophrenia.addNote(oneBeatMiddleC);
    this.schizophrenia.addNote(fourBeatLowMiddleCAtSeven);
    this.schizophrenia.addNote(new Note(Pitch.A, Octave.SEVEN, new Duration(3,
            18)));
  }

  /**
   * Creates the longSong Track.
   */
  private void createLongSong() {
    this.longSong.addNote(new Note(Pitch.A, Octave.SEVEN, new Duration(190,
            18)));
    this.longSong.addNote(new Note(Pitch.C, Octave.FOUR, new Duration(100,
            4)));
    this.longSong.addNote(new Note(Pitch.C, Octave.FOUR, new Duration(7,
            4)));
  }

  /**
   * Creates the monotony Track.
   */
  private void createMonotony() {
    for (int i = 0; i < 300 ; i += 5) {
      this.monotony.addNote(new Note(Pitch.C, Octave.FOUR, new Duration(i, 4)));
    }
    for (int i = 0; i < 300 ; i += 4) {
      this.monotony.addNote(new Note(Pitch.F, Octave.FOUR, new Duration(i, 4)));
    }
  }

  /**
   * Something to do after.
   * @throws Exception fun
   */
  @After
  public void tearDown() throws Exception {
    //    this.model = null;
    //    this.oneBeatMiddleC = null;
    //    this.fourBeatMiddleC = null;
    //    this.factory = null;
    //    this.defTrack = null;
    //    this.nonDefTrack = null;
    //    this.fourFour = null;
    //    this.threeFour = null;
    //    this.oneTwo = null;
    //    this.threeTwo = null;
  }

  @Test
  public void addNote() throws Exception {

    assertEquals("[]", model.getNotes().toString());

    model.addNote(this.oneBeatLowGSharp);

    assertEquals("[G#2]", model.getNotes().toString());
  }

//  @Test(expected = IllegalArgumentException.class)
//  public void addNegativeBeatStart() throws Exception {
//    assertEquals("empty model, default value", this.model.getState());
//
//    model.addNote(this.oneBeatLowGSharp, -1);
//
//    assertEquals("got a one beat low g sharp", this.model.getState());
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void addOnTop() throws Exception {
//    assertEquals("empty model, default value", this.model.getState());
//
//    // Adds a note where there already is one
//    model.addNote(this.oneBeatLowGSharp, 0);
//    model.addNote(this.oneBeatLowGSharp, 0);
//  }
//
//  @Test
//  public void addAtEnd() throws Exception {
//    assertEquals("empty model, default value", this.model.getState());
//
//    model.addNote(this.oneBeatLowGSharp, 100);
//
//    assertEquals("empty model except for low gsharp at 100", model.getState());
//  }
//
//  @Test
//  public void addLongDuration() throws Exception {
//    assertEquals("empty model, default value", this.model.getState());
//
//    // Adds a note where there already is one
//    model.addNote(this.eighteenBeatHighA, 2);
//
//    assertEquals("highA for 18 and that's it", model.getState());
//  }
//
//  @Test
//  public void removeNoteStartingAt() throws Exception {
//    String monoto = monotony.getState();
//
//    this.monotony.removeNoteStartingAt(3, Pitch.C, Octave.EIGHT);
//
//
//    assertNotEquals(monoto, monotony.getState());
//  }

  //  @Test
  //  public void removeNoteOccupying() throws Exception {
  //
  //  }
  //
  //  @Test
  //  public void removeAllOf() throws Exception {
  //
  //  }
  //
  //  @Test
  //  public void removeFirstOf() throws Exception {
  //
  //  }
  //
  //  @Test
  //  public void getMeter() throws Exception {
  //
  //  }
  //
  //  @Test
  //  public void setMeter() throws Exception {
  //
  //  }
  //
  //  @Test
  //  public void startEditor() throws Exception {
  //
  //  }
  //
  //  @Test
  //  public void save() throws Exception {
  //
  //  }
  //
  //  @Test
  //  public void retrieve() throws Exception {
  //
  //  }
  //
  //  @Test
  //  public void equals() throws Exception {
  //
  //
  //  }
  //
  //  @Test
  //  public void testHashCode() throws Exception {
  //
  //  }
  //
  //  @Test
  //  public void mergeWith() throws Exception {
  //
  //  }
  //
  //  @Test
  //  public void combineConsecutively() throws Exception {
  //
  //  }
  //
  //  @Test
  //  public void getAllAtBeat() throws Exception {
  //
  //  }
  //
  //  @Test
  //  public void getAllAtBeat1() throws Exception {
  //
  //  }
  //
  //  @Test
  //  public void overwriteWith() throws Exception {
  //
  //  }
}