package controllers.composites;

import models.User;
import play.Logger;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Http.Cookie;
import play.mvc.Http.Response;
import play.mvc.Result;

public class CookieManagement extends Action<CookieManagement> {
	@Override
	public Result call(Context ctx) throws Throwable {
		Cookie cookie = ctx.request().cookie("crwlzr_id");
		if (cookie == null) {
			User newUser = newCrwlzrUser();
			setCrwlzrCookie(ctx.response(), newUser);
			ctx.session().put("user", "" + newUser._id);
		} else {
			ctx.session().put("user", cookie.value());
		}
		return delegate.call(ctx);
	}
	
	private static void setCrwlzrCookie(Response response, User user) {
		response.setCookie("crwlzr_id", "" + user._id, 20*365*10000);
		Logger.info("set cookie to " + user._id);
	}

	private static User newCrwlzrUser() {
		User user = new User("Joe");
		user.insert();
		return user;
	}
}