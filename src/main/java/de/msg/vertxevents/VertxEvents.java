package de.msg.vertxevents;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;



/**
 * insert lombok
 * 
 * create main method shortcut -> psvm 
 *
 * deploy verticle one with no results
 *
 * deploy verticle two with results
 *
 * implement message timer event 
 * 
 * create evenr enum
 * 
 * receive event and send result with jsonobject
 * 
 * define customobject pojo and customobject message codec
 * 
 * receive event and send result as custom object
 *
 * deploy verticle as worker via deploymentOptions
 */

public class VertxEvents {
    
    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(VertxEvents.class);

        final Vertx vertx = Vertx.vertx();
        
        vertx.eventBus().registerDefaultCodec(CustomObject.class,new CustomObjectCodec());
                
        vertx.deployVerticle(new VerticleOne(), res -> {
            if (res.succeeded()) {
                logger.info("verticle one deployed successfully");
            } else {
                logger.info("verticle one deployed with error : " + res.cause().getLocalizedMessage() );
            }
        });
        
       /**
        vertx.deployVerticle(new VerticleTwo(), new DeploymentOptions().setWorker(true), res -> {
            if (res.succeeded()) {
                logger.info("verticle two deployed successfully");
            } else {
                logger.info("verticle two deployed with error : " + res.cause().getLocalizedMessage());
            }
        });
        */
       
         vertx.deployVerticle(new VerticleTwo(), res -> {
         if (res.succeeded()) {
         logger.info("verticle two deployed successfully");
         } else {
         logger.info("verticle two deployed with error : " + res.cause().getLocalizedMessage());
         }
         });
    }
}
