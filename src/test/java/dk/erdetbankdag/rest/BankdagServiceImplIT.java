package dk.erdetbankdag.rest;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class BankdagServiceImplIT {
    @Test
    public void test() {
        System.out.println("This is a integration test.==============");
        System.out.println(System.getProperty("myURL"));
    	ResteasyClient client = new ResteasyClientBuilder().build();
        Response response = client.target(System.getProperty("myURL")).request().get();
        String responseAsString = response.readEntity(String.class);
        assertEquals("{\"erDetBankDag\":true}", responseAsString);
    }
}
