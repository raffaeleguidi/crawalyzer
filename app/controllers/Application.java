package controllers;

import models.Page;
import models.User;
import play.Logger;
import play.mvc.*;
import views.html.crawalyzer;
import views.html.index;

import com.mongodb.MongoException.DuplicateKey;

import controllers.composites.CookieManagement;
import controllers.composites.Logged;

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
	
	public static Result test(String what) {
        return ok(index.render(user()));
    }

	public static Result crawalyzer(String account) {
    	return ok(crawalyzer.render(account)).as("text/javascript");
    }
    public static Result push() {
    	Page page = new Page();
    	page.url = request().getHeader("Referer");
    	try {
        	page.insert();
        	Logger.info("created: " + page.url);
        	return Results.created("created: " + page.url);
		} catch (DuplicateKey ex) {
        	Logger.info("viewed: " + page.url);
	    	return ok("viewed: " + page.url);
		}
    }
    public static Result push(String what, String title) {
    	Page page = new Page();
    	page.url = what;
    	page.title = title;
    	try {
        	page.insert();
        	return Results.created("created: " + what);
		} catch (DuplicateKey ex) {
	    	return ok("viewed: " + what);
		}
    }
}
