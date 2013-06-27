import java.util.concurrent.TimeUnit;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

import play.*;
import play.libs.Akka;
import scala.concurrent.duration.Duration;

public class Global extends GlobalSettings {
	
	private ActorRef crawler() {
		return Akka.system().actorOf((new Props().withCreator(new UntypedActorFactory() {
		    public UntypedActor create() {
		          return new UntypedActor() {
		            public void onReceive(Object message) {
		              if (message.equals("ciao")) {
		            	  Logger.info("yo");
		              } else {
		                unhandled(message);
		              }
		            }
		          };
		        }
		      }))
		 );
	}
	

	@Override
	public void onStart(Application app) {
		Logger.info("Application has started");
    
	    Akka.system().scheduler().schedule(
		    Duration.create(0, TimeUnit.MILLISECONDS),
		    Duration.create(5, TimeUnit.SECONDS),
		    crawler(), 
		    "ciao",
		    Akka.system().dispatcher()
		);

	}  
  
	@Override
	public void onStop(Application app) {
		Logger.info("Application shutdown...");
	}  
    
}
