import java.util.ArrayList;
/**
 * The class Prime calculates all primes 
 * to a certain maximum value.
 *
 * @author GitYusuf, n-c0de-r
 * @version 12/07/20
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
    private void isPrime(int a) {
        // Check until the square root of the number
        // after that the factors flip anyway.
        for (int n = 2; n <= Math.sqrt(a); n++) {
            // if this is true, it's not a prime
            if (a % n == 0) {
                return;
            }
        }
        // If the loop finishes, nothing is found, it's a prime.
        primes.add(a);
    }

    /**
     * Generates numbers between 2 and a given final number a.
     * Passes each number to isPrime to be checked.
     * @param nr The final number for the loop.
     */
    public void checkNumbers(int nr){
        primes.clear();//clears the list, otherwise they add up!
        
        for(int i = 2; i<=nr; i++){ //generate numbers in a loop
            isPrime(i); //pass the current number to be checked
        }
        
        System.out.println("\nThere are " + primes.size() + " primes"
            +"\nbetween the numbers 0 and " + nr + "\n");
    }

    /**
     * Lists all prime numbers in collection primes.
     */
    public void listAllPrimes() {
        
        for(Integer primeNr : primes) { //for-each-loop
           System.out.println(primeNr);
        }
    }
}
