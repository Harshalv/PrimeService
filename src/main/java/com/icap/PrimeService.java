package com.icap;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.annotation.Documented;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * Root resource (exposed at "prime" path)
 */

@Path("prime")
public class PrimeService {
    static PrimeGenerator primeGenerator = new PrimeGenerator();

    /**
     Title : Check if a number is prime.
     URL : /:num
     Method : GET
     URL Params :  Required: id=[long]
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)
     */
    @GET()
    @Path("{num}")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String isPrime(@PathParam("num") long num) {
        List<Long> primes = primeGenerator.generatePrimeInRange(0, num);
        return primes.contains(num) ? "true" : "false";
    }

    /**
     Title : Generate prime numbers till 'max'
     URL : /till/:max
     Method : GET
     URL Params :  Required: max
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)
     */
    @GET()
    @Path("/till/{max}")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String generatePrimeTill(@PathParam("max") long max) {
        List<Long> primes = primeGenerator.generatePrimeInRange(0, max);
        return primes.toString();
    }

    /**
     Title : Generate prime numbers within a range
     URL : /range?start='start'&till='till'
     Method : GET
     URL Params :  Required: start,till
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)
     */
    @GET()
    @Path("/range")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String generatePrimeInRange(@QueryParam("start") Long start, @QueryParam("till") Long till) {
        ValidateQueryParam(start);
        ValidateQueryParam(till);
        List<Long> primes = primeGenerator.generatePrimeInRange(start, till);
        return primes.toString();
    }

    /**
     Title : Generate prime numbers till 'max' using the Atkins sieve
     URL : /atkins/till/:max
     Method : GET
     URL Params :  Required: max
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)
     */
    @GET()
    @Path("/atkins/till/{max}")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String generatePrimeTillUsingAlgo2(@PathParam("max") long max) {
        List<Long> primes = primeGenerator.generatePrimeInRangeUsingSqrt(0, max);
        return primes.toString();
    }

    /**
     Title : Generate prime numbers within a range using the Atkins sieve
     URL : /atkins/range?start='start'&till='till'
     Method : GET
     URL Params :  Required: start,till
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)
     */
    @GET()
    @Path("/atkins/range")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String generatePrimeInRangeUsingAlgo2(@QueryParam("start") Long start, @QueryParam("till") Long till) {
        ValidateQueryParam(start);
        ValidateQueryParam(till);
        List<Long> primes = primeGenerator.generatePrimeInRangeUsingSqrt(start, till);
        return primes.toString();
    }

    /**
     Title : Generate prime numbers till 'max' using the Atkins mechanism and multithreading. 2nd Fastest
     URL : /threaded/till/:max
     Method : GET
     URL Params :  Required: max
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)
     */
    @GET()
    @Path("/threaded/till/{max}")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String generatePrimeTillMaxUsingThreading(@PathParam("max") long max) {
        List<Long> primes = primeGenerator.generatePrimeInRangeMultiThreaded(0, max);
        return primes.toString();
    }

    /**
     Title : Generate prime numbers within a range using the Atkins sieve and multithreading. 2nd fastest mechanism
     URL : /threaded/range?start='start'&till='till'
     Method : GET
     URL Params :  Required: start,till
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)
     */
    @GET()
    @Path("/threaded/range")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String generatePrimeInRangeUsingThreads(@QueryParam("start") Long start, @QueryParam("till") Long till) {
        ValidateQueryParam(start);
        ValidateQueryParam(till);
        List<Long> primes = primeGenerator.generatePrimeInRangeMultiThreaded(start, till);
        return primes.toString();
    }

    /**
     Title : Generate prime numbers till 'max' using the Atkins seive and java 8 parallel stream. Fastest mechanism
     URL : /parallel/till/:max
     Method : GET
     URL Params :  Required: max
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)
     */
    @GET()
    @Path("/parallel/till/{max}")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String generatePrimeInRangeUsingParallel(@PathParam("max") long max) {
        List<Long> primes = primeGenerator.generatePrimeInRangeUsingParellelStream(0, max);
        return primes.toString();
    }

    /**
     Title : Generate prime numbers within a range using the Atkins sieve and java 8 parallel streams. Fastest mechanism
     URL : /parallel/range?start='start'&till='till'
     Method : GET
     URL Params :  Required: start,till
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)
     */
    @GET()
    @Path("/parallel/range")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String generatePrimeInRangeUsingParallel(@QueryParam("start") Long start, @QueryParam("till") Long till) {
        ValidateQueryParam(start);
        ValidateQueryParam(till);
        List<Long> primes = primeGenerator.generatePrimeInRangeUsingParellelStream(start, till);
        return primes.toString();
    }

    private void ValidateQueryParam(Long param){
        if(param == null) {
            throw new WebApplicationException(
                    Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
                            .entity("name parameter is mandatory")
                            .build()
            );
        }
    }

}
