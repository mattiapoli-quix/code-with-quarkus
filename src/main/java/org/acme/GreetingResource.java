package org.acme;

import io.quarkus.scheduler.Scheduled;
import io.quarkus.vertx.ConsumeEvent;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {


    private static final Logger log = LoggerFactory.getLogger(GreetingResource.class);

    @Inject
    EventBus eventBus;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

        JsonObject message = new JsonObject().put("type", "all");
        eventBus.publish("test-address", message.toString());
        return "Hello RESTEasy";
    }



    @Scheduled(cron = "0/2 * * ? * * *")
    public void test () {
        hello();
    }



}