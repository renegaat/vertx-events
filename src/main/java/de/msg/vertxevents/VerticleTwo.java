package de.msg.vertxevents;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class VerticleTwo extends AbstractVerticle {

    final Logger logger = LoggerFactory.getLogger(VertxEvents.class);
    
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.eventBus().consumer(Events.TIMER_EVENT.toString(),message -> {
            handleMessage(message);
        });
        startFuture.complete();
    }
    
    @Override
    public void stop() throws Exception {
        super.stop();
    }
    
    private void handleMessage(Message message){
        CustomObject customObject = (CustomObject) message.body();
        logger.info("Verticle two reveived custom object : " + customObject.getName());
        message.reply(new JsonObject().put("key","value"));
    }
}
