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
     * Constructor for objects of class Prime
     */
    public Prime()
    {
        primes = new ArrayList<Integer>();
    }

    /**
     * Store a new number into the collection.
     * @param number The number to be stored.
     */
    private void storeNumber(int number)
    {
        primes.add(number);
    }

    /**
     * Checks, if an given number is a prime
     * and adds it to the collection of primes.
     * @param  a The number to be checked.
     */
    private void isPrime(int a) {
        int index = 1; //start check here
        int divisible = 0; //counts divisions without remainder
        while (index != a + 1) //run through all possible divisors
        { 
            if(a % index == 0){ //divide with modulo
                divisible++; //increase the count of divisions
            }
            index++; // take the next number & loop
        }
        if (divisible == 2) //a prime has exactly only 2 divisions
        {
            storeNumber(a); //if it's a prime, store it
        }
    }

    /**
     * Generates numbers between 1 and a given final number a.
     * Passes each number to isPrime to be checked.
     * @param nr The final number for the loop.
     */
    public void inputNumbers(int nr){
        primes.clear();//clears the list, otherwise they add up!
        for(int i = 1; i<=nr; i++){ //generate numbers in a loop
            isPrime(i); //pass the current number to be checked
        }
    }

    /**
     * Lists all prime numbers in collection primes.
     */
    public void listAllPrimes() {
        for(Integer primeNr : primes) { //for-each-loop
            System.out.println(primeNr);
        }
        System.out.println("There are " + primes.size() + " primes in this collection");
    }
}
