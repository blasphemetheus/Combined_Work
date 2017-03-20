package cs3500.music.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs3500.music.model.Note;

/**
 * Created by blewf on 3/18/2017.
 */
public class TextualView implements ViewOperations {

  // MAKE SURE TO USE getNotes() !!!!!!!!!!!!!!

  /**
   * Returns the state of the Model as a String (in columnar form) <p>Technical Specs: - A column of
   * numbers representing the beats, printed right-justified and padded with leading spaces, that is
   * exactly as wide as necessary. (So if your piece is 999 beats long, it uses three columns of
   * characters; if it’s 1000 beats long, it uses four.) - A sequence of columns, each five
   * characters wide, representing each pitch. The first line prints out the names of the pitches,
   * more-or-less centered within the five-character column. I.e., "  F2 " and " G#3 " and " D#10".
   * Because we need to represent at least ten octaves, three-character columns won’t be wide
   * enough.) - Use exactly as many columns as are needed for your piece, from its lowest to its
   * highest note. - Each note-head is rendered as an "  X  ", and each note-sustain is rendered as
   * "  |  ". When a note is not played, five spaces are rendered (as "     "). - As a consequence:
   * every line should be exactly the same length, as shown above. - Every item, including the last
   * one, ends in a newline. - Use the # character (the standard hash or pound sign) to represent
   * sharps, rather than the more correct ♯, to avoid any formatting errors when running your
   * code.</p>
   *
   * @return the state of the game
   */
  String getState();

  @Override
  public String getMusicState() {

    String musicState = "";
    if (Music.isEmpty()) {
      return musicState;
    }
    ///Find end point of song(last note endAt beat)
    ///to set the required space for beats column
    ///We need this now to know where to start our first line
    List<Integer> beats = new ArrayList<>();
    for (int i = 0; i < Music.size(); i++) {
      beats.add(Music.get(i).getDur().endAt);
    }
    ///Add necessary gaps to first line
    int beatMax = Collections.max(beats);
    int bmLength = String.valueOf(beatMax).length();
    for (int i = 0; i < bmLength - 2; i++) {
      musicState += " ";
    }
    ///Accumulate pitches and octaves to empty lists to determine min and max values
    List<Integer> firstline1 = new ArrayList<>();
    List<Integer> firstline2 = new ArrayList<>();
    List<Note> firstline = new ArrayList<>();
    for (int i = 0; i < Music.size(); i++) {
      firstline1.add(Music.get(i).pitch);
    }
    for (int i = 0; i < Music.size(); i++) {
      firstline2.add(Music.get(i).octave);
    }
    int pitchMin = Collections.min(firstline1);
    int pitchMax = Collections.max(firstline1);
    int octaveMin = Collections.min(firstline2);
    int octaveMax = Collections.max(firstline2);

    ///make all possible combination between min and max values and add
    ///to an empty list
    for (int i = octaveMin; i <= octaveMax; i++) {
      for (int j = pitchMin; j <= columns(i, pitchMax); j++) {
        firstline.add(new Note(j, i, null));
      }
    }

    ///loop the list with the correct character spaces between them(5)
    for (int k = 0; k < firstline.size(); k++) {
      switch (firstline.get(k).toString().length()) {
        case 2:
          musicState += "  " + firstline.get(k).toString() + " ";
          break;
        case 3:
          musicState += " " + firstline.get(k).toString() + " ";
          break;
        case 4:
          musicState += " " + firstline.get(k).toString() + "";
          break;
        default:
          musicState += "" + firstline.get(k).toString() + "";
      }
    }
    musicState += "\n";

    ///Now each corresponding line:
    ///Print each line vertically
    for (int i = 0; i < beatMax; i++) {
      musicState += i;
      ///horizontally
      ///loop through 5 character spaces
      for (int j = 0; j < firstline.size(); j++) {
        ///loop through list for possible X or | or empty
        for (int k = 0; k < Music.size(); k++) {
          if (Music.get(k).getDur().startAt == i
                  && Music.get(k).pitch == firstline.get(j).pitch
                  && Music.get(k).octave == firstline.get(j).octave) {
            musicState += "  X  ";
          } else {
            if (Music.get(k).getDur().endAt == i
                    && Music.get(k).pitch == firstline.get(j).pitch
                    && Music.get(k).octave == firstline.get(j).octave) {
              musicState += "  |  ";
            } else {
              if (Music.get(k).getDur().startAt < i
                      && Music.get(k).getDur().endAt >= i
                      && Music.get(k).pitch == firstline.get(j).pitch
                      && Music.get(k).octave == firstline.get(j).octave) {
                musicState += "  |  ";
              }
            }
          }
        }
        if (!(Music.contains(firstline.get(j)))) {
          musicState += "     ";
        }
      }
      musicState += "\n";
    }
    ///required new line at the end
    return musicState += "\n";
  }


  @Override
  public String getState() {
    String output = "";
    Note lowestNote = this.getLowestNote();
    Note highestNote = this.getHighestNote();
    List<Note> encountered = new ArrayList<>();
    List<Integer> encounteredBeats = new ArrayList<>();

    // DEALS WITH FIRST LINE -- SHOULD GENERATE
    // if empty, lowestNote and highestNote should return null values
    if (this.isEmpty()) {
      output.concat(this.firstLine(null, null));
    } else {
      output.concat(this.firstLine(lowestNote, highestNote));
      // if this branch ever returns null lowestnote and null highestNote
      // then I screwed up in getLowestNote() and getHighestNote();
    }

    int totalBeats = this.numBeats();
    for (int currentBeat = 0; currentBeat < totalBeats; currentBeat += 1) {
      if (this.map.containsKey(currentBeat)) {
        for (Note tempNote : this.map.get(currentBeat)) {
          encountered.add(tempNote);
        }
        encountered.addAll(this.map.get(currentBeat));
        encounteredBeats.add(currentBeat);
      }

      // the intro to every line but the first
      output.concat(this.getPaddedInt(currentBeat, totalBeats));
      output.concat(this.genPerBeat(currentBeat, lowestNote, highestNote, encountered));
    }
    return output;
  }

}
