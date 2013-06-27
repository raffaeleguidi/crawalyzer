package controllers;

import com.mongodb.MongoException.DuplicateKey;

import models.User;

import play.*;
import play.mvc.Http.Cookie;
import play.libs.F.Promise;
import play.libs.WS;
import play.libs.WS.Response;
import play.mvc.*;

import views.html.*;
@With(Logged.class)
public class Application extends Controller {
  
    public static User user;
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
    	//Logger.info(respn.getBody());

        return ok(index.render(user));
    }
    
    private static User createCrwlzrUser() {
		User user = new User("Joe");
		user.insert();
		return user;
    }
    private static void setCrwlzrCookie(User user) {
		response().setCookie("crwlzr_id", "" + user._id, 20*365);
    }

    
    public static Result crawalyzer(String account) {
//    	if (user == null) {
//	    	Cookie cookie = request().cookie("crwlzr_id");
//			user = User.findById(cookie.value());
//	    	if (cookie == null || user != null) {
//	    		user = createCrwlzrUser();
//	    		setCrwlzrCookie(user);
//	    		flash().put("warnForCookies", "true");
//	    	}
//    	}
    	return ok(crawalyzer.render(account)).as("text/javascript");
    }
    public static Result push(String what) {
    	return ok(what);
    }
}
