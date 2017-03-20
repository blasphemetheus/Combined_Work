package cs3500.music.model;

import java.util.List;
import java.util.Map;

// HOMEWORK 6 UPDATES:
//    - getState (returns the String representation of the model in columnar form)
//      was moved to the view, so was stuff for Meter (turns out is probs useless)
//    -
//

/**
 * The public-facing interface for the Model for my Music Editor.
 */
public interface ModelOperations {
  
    /**
   * Sets the tempo recorded in this model (in microseconds per beat).
   * (Validates that the 
   */
  void setTempo(int microsecondsPerBeat);

  /**
   * Gets the recorded tempo in the model (in microseconds per beat).
   * @return the tempo (in microseconds per beat)
   */
  int getTempo();

  /**
   * Adds the given note to the Model representation starting with the specified beat.
   * If there is already the exact same note (same duration, beatstart), then it throws an error.
   * Otherwise, it simply adds the note.
   *
   * @param note      the Note to be added
   * @throws IllegalArgumentException if there is already the same note taking up this space
   */
  void addNote(Note note) throws IllegalArgumentException;

  /**
   * A note can be edited the following ways:
   * You can change any of the properties
   * Pitch - increase or decrease the pitch of the note
   * Octave - change the octave of the note
   * Duration - change the placement and increase or decrease the length of the note
   *<p>
   * A note must be changed by providing a note with the same properties that are not to be changed
   * and different properties for those that are to be changed basically just changed the note to be
   * edited with your desired note version. Edit will change all instances of note x with note y.
   *</p>
   * @param x The note to be edited
   * @param y The edited version of the note
   * @throws IllegalArgumentException if the Note to be edit does not exist
   */
  void edit(Note x, Note y) throws IllegalArgumentException;

  /**
   * Removes the Note that starts at the given timeStamp.
   *
   * @param note the note to remove
   * @throws IllegalArgumentException if there is no specified note beginning at the given
   *                                  timeStamp
   */
  void removeNote(Note note) throws IllegalArgumentException;


  /**
   * Removes all Notes of the given Pitch and Octave (regardless of volume or duration).
   *
   * @param pitch  the pitch desired to be removed
   * @param octave the octave desired to be removed
   */
  void removeAllOf(Pitch pitch, Octave octave);

  /**
   * Starts the editor (and creates all the data structures) but doesn't fill it with anything.
   * Sets the tempo to a default of 10000.
   * If called more than once, simply erases everything (except the save)
   * and returns to the initial state.
   */
  void startEditor();

  /**
   * Saves a copy of the current stored Notes in this Model.
   */
  void save();

  /**
   * Retrieves the last copy of stored Notes in this Model and restores it (but nothing else).
   *
   * @throws IllegalStateException if there is no saves
   */
  void retrieve() throws IllegalStateException;

  /**
   * Returns whether this is equal to that Object.
   *
   * @param o that Object
   * @return a boolean representing equality
   */
  boolean equals(Object o);

  /**
   * Gets the hashcode of this ModelOperations.
   *
   * @return the hashcode
   */
  int hashCode();

  /**
   * Merges thatTrack with the stored Track into one Track
   * and stores it.
   *
   * @param thatTrack the Track being merged into this Model
   */
  void mergeWith(ModelOperations thatTrack);

  /**
   * Combines thatTrack with the stored Track by starting thatTrack
   * at the next beat after the stored Track ends.
   *
   * @param thatTrack the Track being appended to the end of the stored Track
   */
  void combineConsecutively(ModelOperations thatTrack);

  /**
   * Gets all the Notes playing on the given beat in a List of Notes.
   *
   * @param beat the given beat
   * @return All the Notes at the given beat in an List
   * @throws IllegalArgumentException if beat points to negative
   */
  List<Note> getAllPlayingAtBeat(int beat);

  /**
   * Stores thatTrack as a new Track(completely replaces the stored Track).
   *
   * @param thatTrack the Track to be stored
   */
  void overwriteWith(ModelOperations thatTrack);


  /**
   * Returns the List of Notes stored in this model.
   * @return the notes
   */
  List<Note> getNotes();

  /**
   * Changes the instrument of this Track to the given instrument.
   *
   * @param instrument the given instrument
   */
  void changeInstrument(Instrument instrument);



  // THIS STUFF IS PROBABLY GOING TO BE PROTECTED OR PRIVATE METHODS IN MODEL IMPLEMENTATION
  /**
   * Returns the number of Beats in the Model.
   *
   * @return the number of Beats in the Model
   */
  int numBeats();

  /**
   * Returns a Note stored with the highest Tone.
   *
   * @return the highest Note
   * @throws IllegalArgumentException if there are no stored notes
   */
  Note getLowestNote() throws IllegalArgumentException;

  /**
   * Returns a Note stored of the lowest tone.
   *
   * @return the lowest note
   * @throws IllegalArgumentException if there are no stored notes
   */
  Note getHighestNote() throws IllegalArgumentException;

  /**
   * Returns whether this has any Notes stored (regardless of Key values stored).
   *
   * @return if this isEmpty
   */
  boolean isEmpty();

  /**
   * Gets the Instrument of the Track.
   *
   * @return the instrument
   */
  Instrument getInstrument();

  /**
   * Gets the Map representation of beats and Notes.
   *
   * @return the map of beats and Notes
   */
  Map<Integer, List<Note>> getMap();

}
