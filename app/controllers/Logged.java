package controllers;

import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;

public class Logged extends Action<Logged> {
	@Override
	public Result call(Context ctx) throws Throwable {
		// TODO Auto-generated method stub
		Logger.info("ctx: " + ctx.request().path());
		return delegate.call(ctx);
	}
}