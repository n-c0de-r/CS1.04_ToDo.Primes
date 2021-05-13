
/**
 * The class Searchengine takes in a note and a
 * term to be looked up and searches for matches.
 *
 * @author GitYusuf, n-c0de-r
 * @version 10.12.2020
 */
public class Searchengine
{
    // instance variables
    private String oneNote;
    private String searchWord;
    private String part1;
    private String part2;
    private String find1;
    private String find2;
    private int index;
    private int joker;

    /**
     * Constructor for objects of class Searchengine
     */
    public Searchengine()
    {
    }

    /**
     * Stores passed arguments in fields.
     * 
     * @param originalNote The original note String from Notebook
     * @param originalWord The original search word String from Notebook
     * @return boolean value of checkJoker.
     */
    public boolean run(String originalNote, String originalWord){
        oneNote = originalNote.toLowerCase(); 
        searchWord = originalWord.toLowerCase();
        return checkJoker(searchWord);
    }

    /**
     * Check the search word to find the joker
     * that determinates the search method.
     * 
     * @param word The word to be looked for.
     * @return The result of corresponding search method.
     */

    private boolean checkJoker(String word){
        if (word.contains("*")){
            index = word.indexOf("*");
            joker = 0;
            return searchStar();
        }else if (word.contains("?")){
            index = word.indexOf("?");
            joker = 1;
            return searchOne();
        } else {return searchFull();}
    }

    /**
     * Split search word at index of joker.
     * 
     * @param word The word to be looked for.
     */

    private void splitWord(String word){
        find1 = word.substring(0, index);
        find2 = word.substring(index + 1, word.length());
    }

    /**
     * Split note at index search word.
     * 
     * @param note Note text to be split.
     */

    private void splitNote(String note){
        part1 = note.substring(0, note.indexOf(find1)+find1.length());
        part2 = note.substring(part1.length()+joker, note.length());
    }

    /**
     * Search current full note with a for-each-loop.
     * @return true if something is found.
     */ 
    private boolean searchFull()
    {
        if(oneNote.contains(searchWord)) {
            return true; //if the term is found
        } else {return false;}
    }

    /**
     * Search current Note parts with regex '*'
     * for any number of characters.
     * @return true if something is found.
     */ 
    private boolean searchStar()
    {
        splitWord(searchWord);
        splitNote(oneNote);
        if(part1.contains(find1) && part2.contains(find2)) {
            return true; //if the term is found
        } else {return false;}
    }

    /**
     * Search current Note parts with regex '?'
     * for one single character.
     * @return true if something is found.
     */ 
    private boolean searchOne()
    {
        splitWord(searchWord);
        splitNote(oneNote);
        if(part1.endsWith(find1) && part2.startsWith(find2)) {
            return true; //if the term is found
        } else {return false;}
    }
}    



