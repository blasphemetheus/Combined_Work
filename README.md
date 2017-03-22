# Combined_Work
with asad



Changes will be logged here:


The combination process for our two designs was a bit lengthy. Asad used a simpler design that stored a List<Note>. Every Note stored a Duration within it, and that Duration records where the Note starts. The information in his model was recorded in only one place - in that Duration. Bradley on the other hand tried to store the starting beat of each Note not within the Note itself, but within a  Map<Integer,List<Note>>.

This would have worked if he kept this consistent, but he also shoehorned in the startingBeat as an integer into his Note class to make it easier. This led to difficulties in keeping everything consistent. There wasn't a single point of truth to where a Note starts. This attempt to make Notes independent of their placement wasn't even that useful for testing, which is the whole reason that Bradley tried to record that data in a Map (specifically a Hashtable), so that he could make a note and then reuse it in a different place in his tests. Silly Bradley. His design was mired down by troubles stemming from not being that versed in working with Map. However, the way Bradley designed his Note is otherwise good (storing Pitch and Octave enumerations that have useful methods - ie can do stuff like output the integer representing the Octave or Pitch and can be used to tell if another Pitch Octave pair is higher or lower or the same)

The combined design incorporates Bradley's enumerations and Asad's much more straightforward List<Note> method of storing Notes. Gotta love foreach loops.

Anyway, once the model was consolidated into a more efficient whole (and the respective tests were adapted over), the rest of the MusicEditor came to be discussed.

The Views (Midi, Textual and Visual), and Controller (complete with Listeners galore) would prove to be the most fun part. Fingers crossed. TO BE CONTINUED ... ... ... .. .. .. . . .  .  .  .


Design:

MCV

Model-- 

ModelOperations- Public-facing interface holding all the methods for our model implementations. 

MusicModel- Represents the model for an instance of the Music Editor.

Other- All other classess in our model folder are representing data. 

View--

ViewOperations- Public-facing interface that holds all the methods that the views need to implement 

TextualView- Represents the state of the model as a string. The techincals on how to represent the model as a string are in the javadoc.

VisualView- Visual representation of the model. This shows the model as a grid that holds all the notes respectively and wether they are starting(onset, BLACK),playing(continuing, GREEN) or ending(absent, BLANK). At the bottom of this grid is piano that represents all pitches and 10 octaves. A red line that can be controlled by the user with the left or right arrow key highlights the beat it is at, on the piano(turning the piano key yellow).  

MidiView- The MidiView stores a copy of the model and initializes the synth and reciever objects in the constructor. the render method starts playback of the model. It should continue until completed and run to the end of the main method. There is no way currently to exit out.

ViewFactory- A factory of views, constructs an apporopiate view based on the String input. The factor takes in only three possible inputs :- "visual", "console" and "midi".

Controller-- 

ControllerOperations- Represents the public facing interface for MusicController, holding the methods to be implemented. 

MusicController- Our Controller for the Music Model, stores model and view and uses keyboard and button listeners.

KeyboardListener- Keeps three maps, one for key typed, pressed and released. Each map stores a key mapping, which is a pair (keystroke and then code to execute with that stroke). The second part of the pair is a function object (object of a class that implements Runnable - one void method).

Button Listener-- The Button Listener class that allows us to deal with button presses, actions.
