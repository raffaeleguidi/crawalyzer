package controllers.composites;

import play.Logger;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

public class Logged extends Action<Logged> {
	@Override
	public Result call(Context ctx) throws Throwable {
		Logger.info("ctx: " + ctx.request().path());
		return delegate.call(ctx);
	}
}