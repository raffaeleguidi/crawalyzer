package controllers;

import controllers.composites.CookieManagement;
import controllers.composites.Logged;

import models.User;

import play.*;
import play.libs.F.Promise;
import play.libs.WS;
import play.libs.WS.Response;
import play.mvc.*;

import views.html.*;

@With({Logged.class, CookieManagement.class})
public class Application extends Controller {
	private static User user() {
		return User.findById(session().get("user"));
	}
	public static Result index() {
    	User user = user();
    	Logger.info("user: " + user);
        return ok(index.render(user));
    }
	
	public static Result crawl() {
		Promise<Response> google = WS.url("http://google.com").get();
		Response respn = google.get();
		Logger.info(respn.getStatusText());
		return null;
	}

       
    public static Result crawalyzer(String account) {
    	return ok(crawalyzer.render(account)).as("text/javascript");
    }
    public static Result push(String what) {
    	return ok(what);
    }
}
