package models;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonProperty;
import org.jongo.MongoCollection;
import org.jongo.Oid;

import uk.co.panaxiom.playjongo.PlayJongo;


public class Page extends JongoModel {
	
    public Page(String name) {
		this.title = name;
	}
    
    public Page() {
    	
    }

    protected static MongoCollection items() {
		MongoCollection pages = PlayJongo.getCollection("pages");
		pages.ensureIndex("{ \"url\": 1 }, { unique: true }");
	    return pages;
	}
    
    public static MongoCollection pages() {
    	return items();
    };

	public static Page findById(String _id) {
		return (Page)findById(_id);
	}

	public String title;
	public String body;

    public static Page findByUrl(String url) {
        return items().findOne("{url: #}", url).as(Page.class);
    }

}