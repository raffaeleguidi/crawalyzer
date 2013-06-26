package controllers;

import com.mongodb.MongoException.DuplicateKey;

import models.User;

import play.*;
import play.libs.F.Promise;
import play.libs.WS;
import play.libs.WS.Response;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
    	User user = new User("Joe");
    	user.number = System.currentTimeMillis();
    	
    	try {
        	user.insert();
		} catch (DuplicateKey e) {
			user = User.findByName("Joe");
	    	user.number = System.currentTimeMillis();
	    	user.save();
		}
    	
    	Promise<Response> google = WS.url("http://google.com").get();
    	Response respn = google.get();
    	Logger.info(respn.getStatusText());
    	Logger.info(respn.getBody());

        return ok(index.render(user));
    }
  
}
