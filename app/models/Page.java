package models;

import java.util.Date;

import org.jongo.MongoCollection;
import org.jongo.Oid;

import uk.co.panaxiom.playjongo.PlayJongo;

import com.mongodb.MongoException.DuplicateKey;


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
    }
    
	public void insert() {
	    items().insert(this);
	}

	public void save() {
	    items().save(this);
	}

	public static JongoModel findById(String _id) {
		return items().findOne(Oid.withOid(_id)).as(Page.class);
	}

	public String url;
	public String title;
	public String body;
	public Date date = new Date();
	public boolean crawled = false;

    public static Page findByUrl(String url) {
        return items().findOne("{url: #}", url).as(Page.class);
    }

	public void saveOrUpdate() {
		try {
			insert();
		} catch (DuplicateKey ex) {
			items().remove("{url: #}", url);
			save();
		}
	}
	
	public void saveOrIncrement() {
		try {
			insert();
		} catch (DuplicateKey ex) {
			items().remove("{url: #}", url);
			save();
		}
	}

}