package tools;

import play.Logger;
import play.libs.WS;
import play.libs.F.Promise;
import play.libs.WS.Response;

public class Crawler {
	public static void crawl(String url) {
		Promise<Response> page = WS.url(url).get();
		Response response = page.get();
		Logger.info(response.getStatusText());
	}
	public static void crawl() {
		Promise<Response> google = WS.url("http://google.com").get();
		Response respn = google.get();
		Logger.info(respn.getStatusText());
	}
}
