import java.util.ArrayList;

/**
 * A class to maintain an arbitrarily long list of notes.
 * Notes are numbered for external reference by a human user.
 * In this version, note numbers start at 0.
 * 
 * @author David J. Barnes and Michael Kolling.
 * @version 2008.03.30
 * @updated 2020.12.07 by GitYusuf and n-c0de-r
 */
public class Notebook
{
    // Storage for an arbitrary number of notes.
    private ArrayList<String> notes;
    private Searchengine searchengine;

    /**
     * Perform any initialization that is required for the
     * notebook.
     */
    public Notebook()
    {
        notes = new ArrayList<String>();
        searchengine = new Searchengine();
    }

    /**
     * Fill the notebook with a few notes for testing.
     */
    public void fillNotebook(){
        storeNote("Erste Notiz");
        storeNote("Zweite Aufzeichnung");
        storeNote("Dr1tter Eintrag");
        storeNote("Mitschrift Nummer 4");
        storeNote("Vermerk Nr fünf");
        storeNote("Sixth Note");
        storeNote("Seventh's a Heaven");    
    }
    
    /**
     * Store a new note into the notebook.
     * @param note The note to be stored.
     */
    public void storeNote(String note)
    {
        notes.add(note);
    }

    /**
     * Remove a note from the notebook.
     * @param noteNumber index of note.
     */
    public void removeNote(int noteNumber)
    {
        if(numberOfNotes() < noteNumber || noteNumber < 0) { //if noteNumber is not within limits
            System.out.println("Error: Note number is not valid.");
        }
        else {
            notes.remove(noteNumber);
        }
    }

    /**
     * @return The number of notes currently in the notebook.
     */
    public int numberOfNotes()
    {
        return notes.size();
    }

    /**
     * Show a note.
     * @param noteNumber The number of the note to be shown.
     */
    public void showNote(int noteNumber)
    {
        if(noteNumber < 0) {
            // This is not a valid note number, so do nothing.
        }
        else if(noteNumber < numberOfNotes()) {
            // This is a valid note number, so we can print it.
            System.out.println(notes.get(noteNumber));
        }
        else {
            // This is not a valid note number, so do nothing.
        }
    }

    /**
     * Show all notes.
     */
    public void listAllNote()
    {
        for(String oneNote : notes) { //for-each-loop
            System.out.println(notes.indexOf(oneNote) + ": " + oneNote);
        }
    }

    /**
     * Search in all notes using regular expressions.
     * You can use a single "?" to replace any single character.
     * Or use a single "*" to replace any number of characters.
     * It's case-INsensitive!
     * @param searchWord The term to be found.
     */
    public void search(String searchWord)
    {
        int termsFound = 0; //counts all findings
        for(String oneNote : notes) { //for-each-loop
            if (searchengine.run(oneNote, searchWord)){
                System.out.println("Found search word '" + 
                    searchWord + "' at position " +     //print this
                    notes.indexOf(oneNote) + ", note: " + oneNote);
                termsFound++; //increase count of found terms
            }
        }
        if (termsFound == 0) { //only if nothing is found, print message
            System.out.println("Search term " +searchWord + " not found.");
        }
    }    

    /**
     * Search in all notes with while-loop.
     * Warning: it's case-sensitive!
     * @param searchWord The term to be found.
     */
    public void searchWhile(String searchWord)
    {
        int termsFound = 0; //counts all findings
        int index = 0; //start with first note
        int sizeOfNotes = numberOfNotes(); //size doesn't change
        while(index != sizeOfNotes){
            if(notes.get(index).contains(searchWord)) { //if the term is found
                System.out.println("Found search word '" +
                    searchWord + "' at position " +     //print this
                    index + ", note: " + notes.get(index));
                termsFound++; //increase count of found terms
            }
            index++; //increase index and restart loop with next
        }
        if (termsFound == 0) { //only if nothing is found, print message
            System.out.println("Search term not found.");
        }
    }
}
