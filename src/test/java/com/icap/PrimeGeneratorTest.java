package com.icap;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Luther on 13/04/2016.
 */
public class PrimeGeneratorTest {

    PrimeGenerator pg;

    @Before
    public void setUp() throws Exception {
        pg = new PrimeGenerator(); //Our prime number generation mechanism
    }

    /**
     *  Tests whether the prime numbers are generated correctly for a range using the simplest algo
     */
    @Test
    public void generatePrimeInRange() {
        List<Long> primes = pg.generatePrimeInRange(0, 1000);
        assertEquals("Should find 168 primes", 168, primes.size());
        primes = pg.generatePrimeInRange(0, 100);
        assertEquals("Should find 25 primes", 25, primes.size());
        primes = pg.generatePrimeInRange(10, 1000);
        assertEquals("Should find 164 primes", 164, primes.size());
    }

    /**
     *  Tests whether the prime numbers are generated correctly for a range using the Atkins seive mechanism
     */
    @Test
    public void generatePrimeInRangeUsingSqrt() {
        List<Long> primes = pg.generatePrimeInRangeUsingSqrt(0, 1000);
        assertEquals("Should find 168 primes", 168, primes.size());
        primes = pg.generatePrimeInRangeUsingSqrt(0, 100);
        assertEquals("Should find 25 primes", 25, primes.size());
        primes = pg.generatePrimeInRangeUsingSqrt(10, 1000);
        assertEquals("Should find 164 primes", 164, primes.size());
    }

    /**
     *  Tests whether the prime numbers are generated correctly for a range using the Atkins mechanism and parallelstream from java 8
     */
    @Test
    public void generatePrimeInRangeUsingParellelStream() {
        List<Long> primes = pg.generatePrimeInRangeUsingParellelStream(0, 1000);
        assertEquals("Should find 168 primes", 168, primes.size());
        primes = pg.generatePrimeInRangeUsingParellelStream(0, 100);
        assertEquals("Should find 25 primes", 25, primes.size());
        primes = pg.generatePrimeInRangeUsingParellelStream(10, 1000);
        assertEquals("Should find 164 primes", 164, primes.size());
    }

    /**
     *  Tests whether the prime numbers are generated correctly for a range using the Atkins mechanism and multithreading
     */    @Test
    public void generatePrimeInRangeMultiThreaded() {
        List<Long> primes = pg.generatePrimeInRangeMultiThreaded(0, 1000);
        assertEquals("Should find 168 primes", 168, primes.size());
        primes = pg.generatePrimeInRangeMultiThreaded(0, 100);
        assertEquals("Should find 25 primes", 25, primes.size());
        primes = pg.generatePrimeInRangeMultiThreaded(10, 1000);
        assertEquals("Should find 164 primes", 164, primes.size());
    }

}