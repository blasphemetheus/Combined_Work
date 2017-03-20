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
  Duration startAtZeroHoldOne;
  Duration startAtOneHoldOne;
  Duration startAtSevenHoldFour;
  Duration startAtThreeHoldEighteen;
  Duration holdFour;

  /**
   * The before, setup thing.
   * @throws Exception fun
   */
  @Before
  public void setUp() throws Exception {

    startAtZeroHoldOne = new Duration(0, 1);
    startAtOneHoldOne = new Duration(1, 1);
    startAtSevenHoldFour = new Duration(7, 4);
    startAtThreeHoldEighteen = new Duration(3, 18);
    holdFour = new Duration(0, 4);

    this.model = new MusicModel();
    this.oneBeatMiddleC = new Note(Pitch.C, Octave.FOUR, startAtZeroHoldOne);
    this.fourBeatMiddleC = new Note(Pitch.C, Octave.FOUR, holdFour);
    this.oneBeatLowGSharp = new Note(Pitch.G$A, Octave.TWO, startAtSevenHoldFour);
    this.eighteenBeatHighA = new Note(Pitch.A, Octave.SEVEN, startAtThreeHoldEighteen);
    this.fourBeatLowMiddleC = new Note(Pitch.C, Octave.FOUR, holdFour);
    this.fourBeatMiddleF = new Note(Pitch.F, Octave.FOUR, holdFour);

    this.schizophrenia = new MusicModel();
    this.createSchizophrenia();

    this.longSong = new MusicModel();
    this.createLongSong();

    this.monotony = new MusicModel();
    this.createMonotony();


    this.thousandBeats = new MusicModel();
    this.thousandBeats.addNote(oneBeatLowGSharp, 999);

    this.nines = new MusicModel();
    this.nines.addNote(oneBeatMiddleC, 998);

    this.oneBeatTrack = new MusicModel();
    this.oneBeatTrack.addNote(this.oneBeatLowGSharp, 0);
  }

  /**
   * Creates the schizophrenia Track.
   */
  private void createSchizophrenia() {
    Duration startAtZeroHoldOne = new Duration(0, 1);
    Duration startAtOneHoldOne = new Duration(1, 1);
    Duration startAtSevenHoldFour = new Duration(7, 4);
    Duration startAtThreeHoldEighteen = new Duration(3, 18);



    this.schizophrenia.addNote(fourBeatLowMiddleC, 0);
    this.schizophrenia.addNote(oneBeatLowGSharp, 1);
    this.schizophrenia.addNote(oneBeatMiddleC, 0);
    this.schizophrenia.addNote(fourBeatLowMiddleC, 7);
    this.schizophrenia.addNote(eighteenBeatHighA, 3);
  }

  /**
   * Creates the longSong Track.
   */
  private void createLongSong() {
    this.longSong.addNote(eighteenBeatHighA, 190);
    this.longSong.addNote(fourBeatLowMiddleC, 100);
    this.longSong.addNote(fourBeatMiddleC, 7);
  }

  /**
   * Creates the monotony Track.
   */
  private void createMonotony() {
    for (int i = 0; i < 300 ; i += 5) {
      this.monotony.addNote(this.fourBeatMiddleC, i);
    }
    for (int i = 0; i < 300 ; i += 4) {
      this.monotony.addNote(this.fourBeatMiddleF, i);
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
  public void getStateEmpty() throws Exception {
    assertEquals("   " + "A4" + "A#4" + "B4" + "C4" + "C#4" + "S4"
            + "D#4" + "E4" + "F4" + "F#4" + "G4" + "G#4", this.model.getState());
  }

  @Test
  public void getStateSchizo() throws Exception {
    assertEquals("the empty model, default value", this.model.getState());

    model.overwriteWith(this.schizophrenia);

    assertEquals("schizophrenia", this.model.getState());
  }

  @Test
  public void getStateMonotony() throws Exception {
    assertEquals("the empty model, default value", this.model.getState());

    model.overwriteWith(this.monotony);

    assertEquals("monotony", this.model.getState());
  }

  @Test
  public void getStateLong() {
    assertEquals("the empty model, default value", this.model.getState());

    this.model.overwriteWith(this.longSong);
    assertEquals("Long model", this.model.getState());
  }


  @Test
  public void getState1000() throws Exception {
    assertEquals("the empty model, default value", this.model.getState());

    model.overwriteWith(this.thousandBeats);

    assertEquals("1000", this.model.getState().substring(0, 8));
  }

  @Test
  public void getState999() throws Exception {
    assertEquals("the empty model, default value", this.model.getState());

    model.overwriteWith(this.nines);

    assertEquals("999", this.model.getState().substring(0, 8));
  }

  @Test
  public void getStateWidth() {
    assertEquals("the empty model, default value", this.model.getState());
    model.overwriteWith(this.oneBeatTrack);

    assertEquals(100, this.model.getState().length());
  }

  @Test
  public void addNoteBeginning() throws Exception {
    assertEquals("empty model, default value", this.model.getState());

    model.addNote(this.oneBeatLowGSharp, 0);

    assertEquals("got a one beat low g sharp", model.getState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNoteNegativeBeatStart() throws Exception {
    assertEquals("empty model, default value", this.model.getState());

    model.addNote(this.oneBeatLowGSharp, -1);

    assertEquals("got a one beat low g sharp", this.model.getState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNoteOnTop() throws Exception {
    assertEquals("empty model, default value", this.model.getState());

    // Adds a note where there already is one
    model.addNote(this.oneBeatLowGSharp, 0);
    model.addNote(this.oneBeatLowGSharp, 0);
  }

  @Test
  public void addNoteAtEnd() throws Exception {
    assertEquals("empty model, default value", this.model.getState());

    model.addNote(this.oneBeatLowGSharp, 100);

    assertEquals("empty model except for low gsharp at 100", model.getState());
  }

  @Test
  public void addNoteLongDuration() throws Exception {
    assertEquals("empty model, default value", this.model.getState());

    // Adds a note where there already is one
    model.addNote(this.eighteenBeatHighA, 2);

    assertEquals("highA for 18 and that's it", model.getState());
  }

  @Test
  public void removeNoteStartingAt() throws Exception {
    String monoto = monotony.getState();

    this.monotony.removeNoteStartingAt(3, Pitch.C, Octave.EIGHT);


    assertNotEquals(monoto, monotony.getState());
  }

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