import java.util.Arrays;
import java.util.ArrayList;
/**
 * Assignment 8
 * The class Prime calculates all primes 
 * to a certain maximum value.
 *
 * @author n-c0de-r
 * @version 20-07-12
 */
public class Prime
{
    /*
     * Integer-type wraps primitive type int, to
     * be treated as an object and be usable by ArrayList.
     */ 
    private ArrayList<Integer> primes;

    /**
     * Constructor for objects of class Prime.
     *
     * @param num   Check all primes up to this number.
     */
    public Prime()
    {
        primes = new ArrayList<Integer>();
    }

    /**
     * Checks, if an given number is a prime
     * and adds it to the collection of primes.
     * @param  a The number to be checked.
     */
    private boolean isPrime(int num) {
        if (num % 2 == 0) { // If the number is divisible by 2
            return false; // it's even and can't be a prime
        }
        
        // Check until the square root of the number
        // after that the factors flip anyway.
        double limit = Math.sqrt(num);
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
     * @param nr The final number for the loop.
     */
    public void checkNumbers(int nr){
        primes.clear();//clears the list, otherwise they add up!
        nr = Math.abs(nr); // Check only positive numbers
        
        if (nr == 2) { // if the number is only 2
            primes.add(nr); // 2 is the only prime
            return; // finish right away
        }
        
        // Start checking the odd number, they can be primes only
        for(int i = 3; i<=nr; i++){ //generate numbers in a loop
            if (isPrime(i)) { // Pass the current number to be checked
                primes.add(i); // Add the found prime
            }
        }
        
        listAllPrimes(nr);
    }
    
    /**
     * Using the Sieve of sieveEratosthenes.
     * Much faster and efficient than the upper code.
     */
    public void sieveEratosthenes(int nr) {
        primes.clear();
        nr = Math.abs(nr);
        
        boolean[] bools = new boolean[nr];
        Arrays.fill(bools, true);
        for (int i = 2; i<Math.sqrt(nr); i++) {
            if(bools[i] == true) {
                for(int j = (i*i); j<nr; j = j+i) {
                    bools[j] = false;
                }
            }
        }
        
        for (int i = 2; i<nr; i++) {
            if (bools[i]) {
                primes.add(i);
            }
        }
        
        listAllPrimes(nr);
    }

    /**
     * Lists all prime numbers in collection primes.
     */
    public void listAllPrimes(int nr) {
        System.out.print('\u000C'); // Clears Terminal
        
        System.out.println("\nThere are " + primes.size() + " primes"
            +"\nbetween the numbers 0 and " + nr + "\n");
            
        for(Integer primeNr : primes) { //for-each-loop
           System.out.println(primeNr);
        }
    }
}
