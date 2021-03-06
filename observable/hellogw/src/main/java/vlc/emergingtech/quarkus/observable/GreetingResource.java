package vlc.emergingtech.quarkus.observable;

import java.time.temporal.ChronoUnit;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/hello")
public class GreetingResource {

    @Inject
    @RestClient
    GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Timed
    @Retry(maxRetries = 2, delay = 2, delayUnit = ChronoUnit.SECONDS)
    @Timeout(value = 1000)
    @Fallback(fallbackMethod = "helloFallback")
    public String hello() {
        return greetingService.hello();
    }

    public String helloFallback() {
        return "hello from fallback.";
    }

}