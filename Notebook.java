import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * Perform any initialization that is required for the
     * notebook. Fill the notebook with test strings at start.
     */
    public Notebook()
    {
        notes = new ArrayList<String>();
        fillNotebook();
    }

    /**
     * Fill the notebook with a few notes for testing.
     */
    public void fillNotebook(){
        storeNote("Erste Notiz");
        storeNote("Zweite Aufzeichnung");
        storeNote("n-c0de-rs Dr1tter Eintrag");
        storeNote("Mitschrift Nummer 4");
        storeNote("Vermerk Nr fünf");
        storeNote("Found Sixth Note on Github");
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
    {   // If noteNumber is not within limits
        if(numberOfNotes() < noteNumber || noteNumber < 0) {
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
        if(noteNumber > 0 && noteNumber < numberOfNotes()) {
            // This is a valid note number, so we can print it.
            System.out.println(notes.get(noteNumber));
        }
    }

    /**
     * Show all notes.
     */
    public void listAllNotes()
    {
        for(String oneNote : notes) { //for-each-loop
            String noteNr = "";
            int nr = notes.indexOf(oneNote);
            
            if (nr <= 9) {
                noteNr = "0" + nr;
            } else {
                noteNr = "" + nr;
            }
            
            System.out.println(noteNr + ": " + oneNote);
        }
    }
    
        /**
     * Search in all notes with while-loop.
     * Warning: it's case-sensitive!
     * @param searchWord The term to be found.
     */
    public void searchFor(String searchWord)
    {
    	boolean termsFound = false; //nothing found yet
    	
        int sizeOfNotes = numberOfNotes(); //size doesn't change
        for (int i = 0; i < sizeOfNotes; i++){
        	
        	// If the term is found print this
            if(notes.get(i).contains(searchWord)) {
            	System.out.println("Found search word '" +
                    searchWord + "' at position " +
                    i + ", note: " + notes.get(i));
            	
            	// Found at least one item, set to true!
                termsFound = true;
            }
        }
        
        // Only if nothing is found, print message
        if (!termsFound) {
        	System.out.println("Search term not found.");
        }
    }
    
    /**
     * Search in all notes with while-loop.
     * Warning: it's case-sensitive!
     * @param searchWord The term to be found.
     */
    public void searchWhile(String searchWord)
    {
    	boolean termsFound = false; //nothing found yet
    	
        int index = 0; //start with first note
        int sizeOfNotes = numberOfNotes(); //size doesn't change
        while(index != sizeOfNotes){
        	
        	// If the term is found print this
            if(notes.get(index).contains(searchWord)) {
            	System.out.println("Found search word '" +
                    searchWord + "' at position " +
                    index + ", note: " + notes.get(index));
            	
            	// Found at least one item, set to true!
                termsFound = true;
            }
            
            // Increase index and move on in the while loop to next item
            index++;
        }
        
        // Only if nothing is found, print message
        if (!termsFound) {
        	System.out.println("Search term not found.");
        }
    }
    
    /**
     * Search in all notes using regular expressions.
     * @param searchWord The term to be found.
     */
    public void search(String searchWord)
    {
    	// Replace any of the characters from task 8
//    	if (searchWord.contains("?")) {
//    		searchWord = searchWord.replaceAll("\\?", "\\.");
//    	}
//    	
//    	if (searchWord.contains("*")) {
//    		searchWord = searchWord.replaceAll("\\*", "\\.*");
//    	}
    	
    	// Create a RegEx search pattern from the given word
    	Pattern pattern = Pattern.compile(searchWord, Pattern.CASE_INSENSITIVE);
    	boolean matchFound = false;
    	
        for(String oneNote : notes) { //for-each-loop
        	// Find matches of the pattern in the given text
        	Matcher matcher = pattern.matcher(oneNote);
        	matchFound = matcher.find();
            
            // If the term is found print this
            if(matchFound) {
            	System.out.println("Found search word '" +
                    searchWord + "' at position " +
                    notes.indexOf(oneNote) + ", note: " + oneNote);
            }
        }
    	
    	if (matchFound) { //only if nothing is found, print message
    		System.out.println("Search term " + searchWord + " not found.");
        }
    }
}
