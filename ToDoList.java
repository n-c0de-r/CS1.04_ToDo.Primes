import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class to maintain an arbitrarily long list of tasks.
 * Tasks are numbered for external reference by a human user.
 * In this version, task numbers start at 0.
 * 
 * @author  David J. Barnes and Michael Kolling.
 * @version 2008.03.30
 * @author  n-c0de-r
 * @version 2023.02.06
 */
public class ToDoList
{
    // Storage for an arbitrary number of tasks.
    private ArrayList<String> tasks;
    /* 
     * Maybe include a field for count of tasks, so
     * it isn't recalculated every time it is needed.
     * Just update it, when a new one is added or removed.
     */
    /**
     * Perform any initialization that is required for the
     * todo-list. Fill the todo-list with test strings at start.
     */
    public ToDoList()
    {
        tasks = new ArrayList<String>();
        fillToDoList(); // Makes tesing easier, until you learn about JUnit.
    }

    /**
     * Store a new task into the todo-list.
     * @param task  The task to be stored.
     */
    public void storeTask(String task)
    {
        tasks.add(task);
    }

    /**
     * Remove a task from the todo-list.
     * @param taskNumber    index of task.
     */
    public void removeTask(int taskNumber)
    {   
        // If taskNumber is not within limits
        if(numberOfTasks() < taskNumber || taskNumber < 0) {
            System.out.println("Error: Task number is not valid."); // Assignment 5
        }
        else {
            tasks.remove(taskNumber); // Assignment 4
        }

        /*
         * One-line solution without if, just math:
         * System.out.println(tasks.remove(Math.abs(taskNumber % numberOfTasks())));
         * or
         * System.out.println(tasks.remove(Math.abs(taskNumber % tasks.size())));
         * 
         * This technically makes if-else unnecessary, but it's what you are supposed
         * to learn, so better go with it. Also You cannot print out error messages, if
         * anything still goes wrong (which shouldn't, but just saying! :P).
         */
    }

    /**
     * Gets the number of tasks on the ToDo-List.
     * @return The number of tasks currently in the todo-list.
     */
    public int numberOfTasks()
    {
        return tasks.size();
        /* Point here is to train you using returns, but the size
         * could be stored in a field instead of getting it from
         * the ArrayList in this case. It's just a design question.
         */
    }

    /**
     * Show a task.
     * @param taskNumber    The number of the task to be shown.
     */
    public void showTask(int taskNumber)
    {
        // Task: The logic is reversed compared to the one in "removeTask()"
        // Both have their usage and are better for certain cases than the other.
        if(taskNumber > 0 && taskNumber < numberOfTasks()) {
            // This is a valid task number, so we can print it.
            System.out.println(tasks.get(taskNumber));
        }

        /*
         * One-line solution without if, just math:
         * System.out.println(tasks.remove(Math.abs(taskNumber % numberOfTasks())));
         * or
         * System.out.println(tasks.remove(Math.abs(taskNumber % tasks.size())));
         * 
         * As before, this makes if-else kinda obsolete!
         */
    }

    /**
     * Show all tasks.
     */
    public void listAllTasks()
    {
        System.out.print('\u000C'); // Clears Terminal
        // Alternatively a "classic" for-loop is good too, as it has indices!
        for(String aTask : tasks) { //for-each-loop
            String taskNr = "";
            // for-each doesn't have indices, so we need to recreate them
            int nr = tasks.indexOf(aTask);

            if (nr <= 9) { // Assignment 3
                taskNr = "0"; // Only add the 0 if needed
            }

            taskNr = "" + nr;

            System.out.println(taskNr + ": " + aTask);
        }
    }

    /**
     * Search in all tasks with while-loop.
     * Warning: it's case-sensitive!
     * @param searchWord    The term to be found.
     */
    public void searchFor(String searchWord)
    {
        boolean termsFound = false; //nothing found yet
        // Assignment 6
        int sizeOfTasks = numberOfTasks(); //size doesn't change
        for (int i = 0; i < sizeOfTasks; i++){

            // If the term is found print this
            if(tasks.get(i).contains(searchWord)) {
                // An extra step before the if could make it case-INsensitive
                System.out.println("Found search word '" +
                    searchWord + "' at position " +
                    i + ", task: " + tasks.get(i));

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
     * Search in all tasks with while-loop.
     * Warning: it's case-sensitive!
     * @param searchWord    The term to be found.
     */
    public void searchWhile(String searchWord)
    {
        boolean termsFound = false; //nothing found yet
        // Assignment 7
        int index = 0; //start with first task
        int sizeOfTasks = numberOfTasks(); //size doesn't change
        while(index != sizeOfTasks){

            // If the term is found print this
            if(tasks.get(index).contains(searchWord)) {
                System.out.println("Found search word '" +
                    searchWord + "' at position " +
                    index + ", task: " + tasks.get(index));

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
     * Search in all tasks using regular expressions.
     * @param searchWord    The term to be found.
     */
    public void search(String searchWord)
    {
        // Assignment 9
        // Replace any of the characters from task 8
        //        if (searchWord.contains("?")) {
        //            searchWord = searchWord.replaceAll("\\?", "\\.");
        //        }
        //        
        //        if (searchWord.contains("*")) {
        //            searchWord = searchWord.replaceAll("\\*", "\\.*");
        //        }
        
        // Assignment 10
        // Create a RegEx search pattern from the given word
        Pattern pattern = Pattern.compile(searchWord, Pattern.CASE_INSENSITIVE);
        boolean matchFound = false;

        for(String aTask : tasks) { //for-each-loop
            // Find matches of the pattern in the given text
            Matcher matcher = pattern.matcher(aTask);
            matchFound = matcher.find();

            // If the term is found print this
            if(matchFound) {
                System.out.println("\nFound search word '" +
                    searchWord + "'\nat position " +
                    tasks.indexOf(aTask) + ", task:\n" + aTask + "\n");
            }
        }

        if (!matchFound) { //only if nothing is found, print message
            System.out.println("Search term " + searchWord + " not found.");
        }
    }

    /**
     * Fill the todo-list with a few tasks for testing.
     */
    private void fillToDoList(){
        storeTask("First Task");
        storeTask("Second Chore");
        storeTask("n-c0de-r's third entry");
        storeTask("ToDo Number 4");
        storeTask("Duty Nr five");
        storeTask("Found Sixth Task on Github");
        storeTask("Seventh job's a Heaven");    
    }
}
