package com.icap;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;

public class PrimeServiceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test(expected = javax.ws.rs.NotFoundException.class)
    public void Test404ExceptionForPrime(){
        String responseMsg = target.path("prime").request().get(String.class);
    }

    @Test(expected = javax.ws.rs.NotFoundException.class)
    public void Test404ExceptionForTill(){
        String responseMsg = target.path("prime/till").request().get(String.class);
    }

    @Test(expected = javax.ws.rs.BadRequestException.class)
    public void Test404ExceptionForRange(){
        String responseMsg = target.path("prime/range").request().get(String.class);
    }

    @Test(expected = javax.ws.rs.NotFoundException.class)
    public void Test404ExceptionForAtkinsTill(){
        String responseMsg = target.path("prime/atkins/till").request().get(String.class);
    }

    @Test(expected = javax.ws.rs.BadRequestException.class)
    public void Test404ExceptionForAtkinsRange(){
        String responseMsg = target.path("prime/atkins/range").request().get(String.class);
    }

    @Test(expected = javax.ws.rs.NotFoundException.class)
    public void Test404ExceptionForThreadedTill(){
        String responseMsg = target.path("prime/threaded/till").request().get(String.class);
    }

    @Test(expected = javax.ws.rs.BadRequestException.class)
    public void Test404ExceptionForThreadedRange(){
        String responseMsg = target.path("prime/threaded/range").request().get(String.class);
    }

    @Test(expected = javax.ws.rs.NotFoundException.class)
    public void Test404ExceptionForParallelTill(){
        String responseMsg = target.path("prime/parallel/till").request().get(String.class);
    }

    @Test(expected = javax.ws.rs.BadRequestException.class)
    public void Test404ExceptionForParallelRange(){
        String responseMsg = target.path("prime/parallel/range").request().get(String.class);
    }

    /**
     * Test whether the service identifies the prime number correctly
     */
    @Test
    public void testPrimeNumber() {
        String responseMsg = target.path("prime/3").request().get(String.class);
        assertEquals("true", responseMsg);
        responseMsg = target.path("prime/2").request().get(String.class);
        assertEquals("true", responseMsg);
        responseMsg = target.path("prime/1").request().get(String.class);
        assertEquals("false", responseMsg);
        responseMsg = target.path("prime/0").request().get(String.class);
        assertEquals("false", responseMsg);
        responseMsg = target.path("prime/-3").request().get(String.class);
        assertEquals("false", responseMsg);
        responseMsg = target.path("prime/997").request().get(String.class);
        assertEquals("true", responseMsg);

    }


    /**
     * Tests the service to generate primes from 0 to {max} using the simplest algorithm (Eratosthenes)
     */
    @Test
    public void testPrimeTill() {
        String responseMsg = target.path("prime/till/3").request().get(String.class);
        assertEquals("[2, 3]", responseMsg);
        responseMsg = target.path("prime/till/2").request().get(String.class);
        assertEquals("[2]", responseMsg);
        responseMsg = target.path("prime/till/1").request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/till/0").request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/till/-3").request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/till/1000").request().get(String.class);
        assertEquals(168, responseMsg.split(" ").length);
    }

    /**
     * Tests the service to generate primes for a range using the simplest algorithm (Eratosthenes)
     */
    @Test
    public void testPrimeRange() {
        String responseMsg = target.path("prime/range").queryParam("start", 0).queryParam("till", 1000).request().get(String.class);
        assertEquals(168, responseMsg.split(" ").length);
        responseMsg = target.path("prime/range").queryParam("start", 10).queryParam("till", 1000).request().get(String.class);
        assertEquals(164, responseMsg.split(" ").length);
        responseMsg = target.path("prime/range").queryParam("start", 0).queryParam("till", 100).request().get(String.class);
        assertEquals(25, responseMsg.split(" ").length);
        responseMsg = target.path("prime/range").queryParam("start", 0).queryParam("till", -50).request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/range").queryParam("start", -10).queryParam("till", -50).request().get(String.class);
        assertEquals("[]", responseMsg);
    }

    /**
     * Tests the service to generate primes from 0 to {max} using the Atkins seive mechanism
     */
    @Test
    public void testPrimeAtkinsTill() {
        String responseMsg = target.path("prime/atkins/till/3").request().get(String.class);
        assertEquals("[2, 3]", responseMsg);
        responseMsg = target.path("prime/atkins/till/2").request().get(String.class);
        assertEquals("[2]", responseMsg);
        responseMsg = target.path("prime/atkins/till/1").request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/atkins/till/0").request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/atkins/till/-3").request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/atkins/till/1000").request().get(String.class);
        assertEquals(168, responseMsg.split(" ").length);
    }

    /**
     * Tests the service to generate primes for a range using the Atkins seive mechanism
     */
    @Test
    public void testPrimeAtkinsRange() {
        String responseMsg = target.path("prime/atkins/range").queryParam("start", 0).queryParam("till", 1000).request().get(String.class);
        assertEquals(168, responseMsg.split(" ").length);
        responseMsg = target.path("prime/atkins/range").queryParam("start", 10).queryParam("till", 1000).request().get(String.class);
        assertEquals(164, responseMsg.split(" ").length);
        responseMsg = target.path("prime/atkins/range").queryParam("start", 0).queryParam("till", 100).request().get(String.class);
        assertEquals(25, responseMsg.split(" ").length);
        responseMsg = target.path("prime/atkins/range").queryParam("start", 0).queryParam("till", -50).request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/atkins/range").queryParam("start", -10).queryParam("till", -50).request().get(String.class);
        assertEquals("[]", responseMsg);
    }

    /**
     * Tests the service to generate primes from 0 to {max} using multiple threads
     */
    @Test
    public void testPrimeThreadedTill() {
        String responseMsg = target.path("prime/threaded/till/3").request().get(String.class);
        assertEquals("[2, 3]", responseMsg);
        responseMsg = target.path("prime/threaded/till/2").request().get(String.class);
        assertEquals("[2]", responseMsg);
        responseMsg = target.path("prime/threaded/till/1").request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/threaded/till/0").request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/threaded/till/-3").request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/threaded/till/1000").request().get(String.class);
        assertEquals(168, responseMsg.split(" ").length);
    }

    /**
     * Tests the service to generate primes for a range using the multiple threads
     */
    @Test
    public void testPrimeThreadedRange() {
        String responseMsg = target.path("prime/threaded/range").queryParam("start", 0).queryParam("till", 1000).request().get(String.class);
        assertEquals(168, responseMsg.split(" ").length);
        responseMsg = target.path("prime/threaded/range").queryParam("start", 10).queryParam("till", 1000).request().get(String.class);
        assertEquals(164, responseMsg.split(" ").length);
        responseMsg = target.path("prime/threaded/range").queryParam("start", 0).queryParam("till", 100).request().get(String.class);
        assertEquals(25, responseMsg.split(" ").length);
        responseMsg = target.path("prime/threaded/range").queryParam("start", 0).queryParam("till", -50).request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/threaded/range").queryParam("start", -10).queryParam("till", -50).request().get(String.class);
        assertEquals("[]", responseMsg);
    }

    /**
     * Tests the service to generate primes from 0 to {max} using the parallel stream mechanism in java 8
     */
    @Test
    public void testPrimeTillUsingParallelStream() {
        String responseMsg = target.path("prime/parallel/till/3").request().get(String.class);
        assertEquals("[2, 3]", responseMsg);
        responseMsg = target.path("prime/parallel/till/2").request().get(String.class);
        assertEquals("[2]", responseMsg);
        responseMsg = target.path("prime/parallel/till/1").request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/parallel/till/0").request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/parallel/till/-3").request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/parallel/till/1000").request().get(String.class);
        assertEquals(168, responseMsg.split(" ").length);
    }

    /**
     * Tests the service to generate primes for a range using the parallel stream mechanism in java 8
     */
    @Test
    public void testPrimeRangeUsingParallelStream() {
        String responseMsg = target.path("prime/parallel/range").queryParam("start", 0).queryParam("till", 1000).request().get(String.class);
        assertEquals(168, responseMsg.split(" ").length);
        responseMsg = target.path("prime/parallel/range").queryParam("start", 10).queryParam("till", 1000).request().get(String.class);
        assertEquals(164, responseMsg.split(" ").length);
        responseMsg = target.path("prime/parallel/range").queryParam("start", 0).queryParam("till", 100).request().get(String.class);
        assertEquals(25, responseMsg.split(" ").length);
        responseMsg = target.path("prime/parallel/range").queryParam("start", 0).queryParam("till", -50).request().get(String.class);
        assertEquals("[]", responseMsg);
        responseMsg = target.path("prime/parallel/range").queryParam("start", -10).queryParam("till", -50).request().get(String.class);
        assertEquals("[]", responseMsg);
    }

}
