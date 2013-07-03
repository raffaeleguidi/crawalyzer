package models;

import java.util.Date;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.Oid;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import play.libs.F.Promise;
import play.libs.WS;
import uk.co.panaxiom.playjongo.PlayJongo;

import com.mongodb.MongoException.DuplicateKey;


public class PageViews extends JongoModel {
	
    public ObjectId _pageId;
    public ObjectId _userId;
    public Date date = new Date();

	public PageViews (String url, ObjectId userId) {
		this._pageId = Page.findByUrl(url)._id;
		this._userId = userId;
	}
	
    protected static MongoCollection items() {
		MongoCollection pageviews = PlayJongo.getCollection("pageviews");
	    return pageviews;
	}
        
    public static MongoCollection pageViews() {
    	return items();
    }
    
	public void insert() {
	    items().insert(this);
	}

	public void save() {
	    items().save(this);
	}

	public static JongoModel findById(String _id) {
		return items().findOne(Oid.withOid(_id)).as(PageViews.class);
	}
}