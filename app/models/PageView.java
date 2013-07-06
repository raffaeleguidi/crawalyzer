package models;

import java.util.Date;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.Oid;

import uk.co.panaxiom.playjongo.PlayJongo;

public class PageView extends JongoModel {
	
    public ObjectId _pageId;
    public ObjectId _userId;
    public Date date = new Date();

	public PageView (ObjectId pageId, ObjectId userId) {
		this._pageId = pageId;
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
		return items().findOne(Oid.withOid(_id)).as(PageView.class);
	}
}