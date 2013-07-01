import java.util.concurrent.TimeUnit;

import models.Page;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

public class Global extends GlobalSettings {
	
	@SuppressWarnings("serial")
	private ActorRef crawler() {
		return Akka.system().actorOf((new Props().withCreator(new UntypedActorFactory() {
		    public UntypedActor create() {
		         return new UntypedActor() {
		        	 public void onReceive(Object message) {
		             	 if (message.equals("crawl")) {
			        		Page page = Page.pages().findOne("{crawled: false}").as(Page.class);
			        		if (page != null) {
				            	Logger.info("crawling " + page.url);
				            	page.crawl();
				            	page.save();
			        		} else {
			        			Logger.info("nothing to crawl");
			        		}
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

		// setup indices
		
		// end setup indices
		
	    Akka.system().scheduler().schedule(
		    Duration.create(0, TimeUnit.MILLISECONDS),
		    Duration.create(5, TimeUnit.SECONDS),
		    crawler(), 
		    "crawl",
		    Akka.system().dispatcher()
		);

	}  
  
	@Override
	public void onStop(Application app) {
		Logger.info("Application shutdown...");
	}  
    
}
