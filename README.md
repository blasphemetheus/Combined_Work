# Combined_Work
with asad



Changes will be logged here:


The combination process for our two designs was a bit lengthy. Asad used a simpler design that stored a List<Note>. Every Note stored a Duration within it, and that Duration records where the Note starts. The information in his model was recorded in only one place - in that Duration. Bradley on the other hand tried to store the starting beat of each Note not within the Note itself, but within a  Map<Integer,List<Note>>.

This would have worked if he kept this consistent, but he also shoehorned in the startingBeat as an integer into his Note class to make it easier. This led to difficulties in keeping everything consistent. There wasn't a single point of truth to where a Note starts. This attempt to make Notes independent of their placement wasn't even that useful for testing, which is the whole reason that Bradley tried to record that data in a Map (specifically a Hashtable), so that he could make a note and then reuse it in a different place in his tests. Silly Bradley. His design was mired down by troubles stemming from not being that versed in working with Map. However, the way Bradley designed his Note is otherwise good (storing Pitch and Octave enumerations that have useful methods - ie can do stuff like output the integer representing the Octave or Pitch and can be used to tell if another Pitch Octave pair is higher or lower or the same)

The combined design incorporates Bradley's enumerations and Asad's much more straightforward List<Note> method of storing Notes. Gotta love foreach loops.

Anyway, once the model was consolidated into a more efficient whole (and the respective tests were adapted over), the rest of the MusicEditor came to be discussed.

The Views (Midi, Textual and Visual), and Controller (complete with Listeners galore) would prove to be the most fun part. Fingers crossed. TO BE CONTINUED ... ... ... .. .. .. . . .  .  .  .
