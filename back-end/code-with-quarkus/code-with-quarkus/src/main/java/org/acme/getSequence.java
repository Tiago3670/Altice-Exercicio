package org.acme;

import java.math.BigInteger;
import java.util.HashMap;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/labseq/{n}")
public class getSequence {
    private static final Logger log = Logger.getLogger(getSequence.class); 
    HashMap<Integer, BigInteger> historyMap = new HashMap<>(); 

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(@PathParam("n") Integer  n) { 
        if (n < 0) { // n is negative
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error: n must be positive")
                    .build();
        }
        else if (n > 10000) { // n is too large
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error: n must be less than 10 000")
                    .build();
        }
        else // n is positive
        {

            BigInteger result = l(n);
            ResponseFormat response = new ResponseFormat(n, result.toString(0));
            return Response.ok(response).build();
        }
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public class ResponseFormat { // class for response
        private int n;
        private String value;
    
        public ResponseFormat(int n, String string) {
            this.n = n;
            this.value = string;
        }
    
    }

    public BigInteger l(Integer n) {
        
        BigInteger res;
        
        if (historyMap.containsKey(n)) {
            // log.info("Found in history"); 
            return historyMap.get(n); // found in history
        }
        else 
        if (n == 0) {
            res=BigInteger.ZERO;
        }
        else if (n == 1) {
            res= BigInteger.ONE;
        }
        else if (n == 2) {
            res= BigInteger.ZERO;
        }
         else if (n == 3) {
            res= BigInteger.ONE;
        }
        else
        {
           res=l(n-4).add(l(n-3));
        }
        if (historyMap.containsKey(n)==false) { 
                    historyMap.put(n, res);  // add to history
        }
        
        return res;
    }
}
