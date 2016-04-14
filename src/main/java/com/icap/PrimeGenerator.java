package com.icap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.LongStream;

import static java.lang.Math.sqrt;

/**
 * A class that generates or checks for prime numbers
 */
public class PrimeGenerator {

    /**
     * Generates the prime numbers using a simple algo (sieve of Eratosthenes)
     *
     * @param start - prime numbers starting from
     * @param till  - prime numbers till
     * @return a list of prime number between the range of start and till
     */
    public List<Long> generatePrimeInRange(long start, long till) {
        final List<Long> primes = new ArrayList<>(); // hold all primes from 1 to 'till'
        final List<Long> rangeList = new ArrayList<>(); // primes only within start and till
        LongStream.rangeClosed(0, till). // linear filtering mechanism
                filter(i -> isPrime(primes, i)).
                forEach(primes::add);
        // filter primes only between start and till
        primes.stream().filter(i -> i > start && i < till + 1).forEach(rangeList::add);
        return rangeList;
    }

    /**
     * Helper method to find primality of a number
     * @param primes list of prime numbers below 'i'
     * @param i -  the number to check for primality
     * @return - true is the number is prime, else false
     */
    private boolean isPrime(List<Long> primes, long i) {
        if (i < 2)
            return false;
        for (long prime : primes)
            if (prime > 1) {
                if (i % prime == 0) {
                    return false;
                }
            }
        return true;
    }

    /**
     * Generates the prime numbers using a simple algo (sieve of Atkins)
     *
     * @param start - prime numbers starting from
     * @param till  - prime numbers till
     * @return a list of prime number between the range of start and till
     */
    public List<Long> generatePrimeInRangeUsingSqrt(long start, long till) {
        final List<Long> primes = new ArrayList<>(); // hold all primes from 1 to 'till'
        final List<Long> rangeList = new ArrayList<>(); // primes only within start and till
        LongStream.rangeClosed(1, till). // we start from 1 since we need all primes previous to 'till' for this algo
                filter(i -> isPrime(i)).
                forEach(primes::add);
        primes.stream().filter(i -> i > start && i < till + 1).forEach(rangeList::add); // filter primes only between start and till
        return rangeList;
    }

    /**
     * Generates the prime numbers using a simple algo (sieve of Eratosthenes) and parallelstream mechanism in java 8
     * Using current stats on a core2/2gb ram machine , this is the fastest mechanism
     *
     * @param start - prime numbers starting from
     * @param till  - prime numbers till
     * @return a list of prime number between the range of start and till
     */
    public List<Long> generatePrimeInRangeUsingParellelStream(long start, long till) {
        //Could have used CopyOnWrite list but think that would be inefficient.
        ConcurrentLinkedQueue<Long> primeQ = new ConcurrentLinkedQueue<Long>(); //using a threadsafe collection
        LongStream.rangeClosed(start, till).parallel(). // the filter and subsequent adding happens parallel for each number in the range
                filter(i -> isPrime(i)). //if prime number
                forEach(primeQ::add);  // add to concurrent queue

        List<Long> primes = new ArrayList<>();
        //convert queue to list.
        primeQ.stream().forEach(primes::add);
        return primes;
    }

    // Helper method doing it the atkins way
    private boolean isPrime(long num) {
        if (num < 2) // prime numbers are positive and above 1
            return false;
        long max = (long) sqrt(num); // get the rounded square root of the number to check for primality
        for (int j = 2; j <= max; j++) {
            if (num % j == 0) {
                return false; // not a prime number as is divisible
            }
        }
        return true; // optimus prime :-)
    }

    /**
     * Generates the prime numbers using a simple algo (sieve of Eratosthenes) and multithreading
     * Using current stats on a core2/2gb ram machine , this is the 2nd fastest mechanism
     *
     * @param start - prime numbers starting from
     * @param till  - prime numbers till
     * @return a list of prime number between the range of start and till
     */
    public List<Long> generatePrimeInRangeMultiThreaded(long start, long till) {
        //Could have used CopyOnWrite list but think that would be inefficient.
        ConcurrentLinkedQueue<Long> primesQ = new ConcurrentLinkedQueue<>(); //using a threadsafe collection
        ExecutorService executor = Executors.newFixedThreadPool(10); // no particular reason to keep them to 10

        // worker thread class to calculate whether a number is prime
        class worker implements Runnable {
            long workOn; //the number to check for Primality

            worker(long workOn) {
                this.workOn = workOn;
            }

            @Override
            public void run() {
                if (isPrime(workOn)) {
                    primesQ.add(workOn); // add to central collection
                }
            }
        }

        List<Future> futures = new ArrayList<>();
        for (long num = start; num <= till; num++) {
            // keep a list of futures to check whether all threads have completed thier job
            futures.add(executor.submit(new worker(num)));
        }

        for (Future f : futures) {
            try {
                f.get(); // blocks till it completes
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } // by the end , we should be pretty sure that the threads have completed
        executor.shutdown(); //stop the executor

        List<Long> primes = new ArrayList<>();
        primesQ.stream().forEach(primes::add); // convert concurrent queue to List
        return primes;
    }
}
