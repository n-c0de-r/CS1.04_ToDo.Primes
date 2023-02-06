import java.util.Arrays;
import java.util.ArrayList;
/**
 * Assignment 8
 * The class Prime calculates all primes 
 * to a certain given maximum value.
 *
 * @author  n-c0de-r
 * @version 2023.02.06
 */
public class Prime
{
    /*
     * Integer-type wraps primitive type int, to be
     * treated as an object and be usable by ArrayList.
     */ 
    private ArrayList<Integer> primes;

    /**
     * Constructor for objects of class Prime.
     */
    public Prime()
    {
        primes = new ArrayList<Integer>();
    }

    /**
     * Checks, if an given number is a prime
     * and adds it to the collection of primes.
     * @param  num  The number to be checked.
     */
    private boolean isPrime(int num) {
        if (num % 2 == 0) { // If the number is divisible by 2
            return false; // it's even and can't be a prime
        }
        
        // Check until the square root of the number
        // after that the factors flip anyway.
        double limit = Math.sqrt(num);
        // Start with 3 as 1 and 2 aren't primes and skip every 2nd number as they are even
        for (int n = 3; n <= limit; n+=2) {
            // if this is true, it's not a prime
            if (num % n == 0) { // If the number is divisible by an
                return false; // factor in the middle, it's not a prime
            }
        }
        // If the loop finishes, nothing is found, it's a prime.
        return true;
    }

    /**
     * Generates numbers between 2 and a given final number.
     * Passes each number to isPrime to be checked.
     * @param num    The final number for the loop.
     */
    public void checkNumbers(int num){
        primes.clear();//clears the list, otherwise they add up!
        num = Math.abs(num); // Check only positive numbers
        
        if (num < 2) { // if the number is smaller than 2
            return; // finish right away, there are no primes
        }
        
        primes.add(2); // 2 is the only even number to always include
        
        // Start checking the odd number, they can be primes only
        for(int i = 3; i<=num; i++){ //generate numbers in a loop
            if (isPrime(i)) { // Pass the current number to be checked
                primes.add(i); // Add the found prime
            }
        }
        
        listAllPrimes(num);
    }
    
    public void sieveEratosthenes(int num) {
        primes.clear(); // Clear the list, just in case.
        num = Math.abs(num); // Protect from entering negative numbers.
        
        if (num < 2) { // if the number is smaller than 2
            return; // finish right away, there are no primes
        }
        
        // Create a new array of booleans for all numbers
        boolean[] bools = new boolean[num];
        // Overwrite all array values to "true" - default is false.
        Arrays.fill(bools, true);
        
        // Preemptively remove all even numbers, reduces the workload
        for(int j = (2*2); j<num; j = j+2) {
            bools[j] = false; // Can be about 60% faster!
        }
        
        // Similar as before check only up to the square root of the number
        double limit = Math.sqrt(num);
        // Only check every 2nd number, starting with 3, so only odds
        for (int i = 3; i<limit; i+=2) {
            // But only if it is still true, check further
            if(bools[i] == true) {
                // Check if the number has multiples in-between,
                for(int j = (i*i); j<num; j = j+i) {
                    // Remove all multiples from further checks in advance
                    bools[j] = false; // Primes have no multiples.
                }
            }
        }
        
        // Add primes to the end result list
        for (int i = 2; i<num; i++) {
            if (bools[i]) {
                primes.add(i);
            }
        }
        
        listAllPrimes(num);
    }
    
    /**
     * Lists all prime numbers in collection primes.
     * @param nr    The highest number.
     */
    private void listAllPrimes(int nr) {
        System.out.print('\u000C'); // Clears Terminal
        
        System.out.println("There are " + primes.size() + " primes"
            +"\nbetween the numbers 0 and " + nr + "\n");
        
        String result = ""; 
        for(Integer prime : primes) { //for-each-loop
            result += prime + ", ";
            
            int index = primes.indexOf(prime);
            if(index % 10 == 0 && index > 0) {
                result += "\n"; // Add a new line every 10 items
            }
        }
        result = result.substring(0, result.length()-2);
        System.out.println(result);
    }
}
