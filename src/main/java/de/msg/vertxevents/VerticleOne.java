package de.msg.vertxevents;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class VerticleOne extends AbstractVerticle {

    final Logger logger = LoggerFactory.getLogger(VertxEvents.class);
    
    @Override
    public void start(Future<Void> startFuture) throws Exception {

        vertx.setPeriodic(1000, aLong -> {
            sendMessage();
        });
        startFuture.complete();
    }
    
    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        //clean up code
        super.stop(stopFuture);
    }
    
    private void sendMessage() {
        logger.info("Verticle one is sending a timer event");
        
        CustomObject customObject = CustomObject.builder().id(1).name("myname").build(); 
                
        vertx.eventBus().send(Events.TIMER_EVENT.toString(), customObject, message -> {
            logger.info("Verticle one received : " + message.result().body());
        });
    }
}
