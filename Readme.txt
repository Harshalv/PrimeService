Built using Idea Intellij and Maven.

The project is developed using the following env.
Java jdk1.8.0_73
jersey.version 2.17 - Rest service implementation
grizzly2-http -  standalone web server to host our rest service.

All dependencies are included in maven.

You can also run the

Running the com.icap.main class starts the web server
The rest service is available at

http://localhost:8080/icap/

Currently available urls are


     Title : Check if a number is prime.
     URL : /:num
     Method : GET
     URL Params :  Required: id=[long]
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)

     Title : Generate prime numbers till 'max'
     URL : /till/:max
     Method : GET
     URL Params :  Required: max
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)

     Title : Generate prime numbers within a range
     URL : /range?start='start'&till='till'
     Method : GET
     URL Params :  Required: start,till
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)

     Title : Generate prime numbers till 'max' using the Atkins sieve
     URL : /atkins/till/:max
     Method : GET
     URL Params :  Required: max
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)

     Title : Generate prime numbers within a range using the Atkins sieve
     URL : /atkins/range?start='start'&till='till'
     Method : GET
     URL Params :  Required: start,till
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)

     Title : Generate prime numbers till 'max' using the Atkins mechanism and multithreading. 2nd Fastest
     URL : /threaded/till/:max
     Method : GET
     URL Params :  Required: max
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)

     Title : Generate prime numbers within a range using the Atkins sieve and multithreading. 2nd fastest mechanism
     URL : /threaded/range?start='start'&till='till'
     Method : GET
     URL Params :  Required: start,till
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)

     Title : Generate prime numbers till 'max' using the Atkins seive and java 8 parallel stream. Fastest mechanism
     URL : /parallel/till/:max
     Method : GET
     URL Params :  Required: max
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)

     Title : Generate prime numbers within a range using the Atkins sieve and java 8 parallel streams. Fastest mechanism
     URL : /parallel/range?start='start'&till='till'
     Method : GET
     URL Params :  Required: start,till
     Response Codes: Success (200 OK), Bad Request (400), Unauthorized (401)
