package models;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonProperty;
import org.jongo.MongoCollection;
import org.jongo.Oid;

import uk.co.panaxiom.playjongo.PlayJongo;

public class User {
	
    public User(String name) {
		this.name = name;
	}
    
    public User() {
    	
    }

	public static MongoCollection users() {
		MongoCollection users = PlayJongo.getCollection("users");
        return users;
    }

    public ObjectId _id;
	public long number;
	public String name;

    public void insert() {
        users().insert(this);
    }
    public void save() {
        users().save(this);
    }

    public void remove() {
        users().remove( _id);
    }

    public static User findByName(String name) {
        return users().findOne("{name: #}", name).as(User.class);
    }

	public static User findById(String _id) {
		return users().findOne(Oid.withOid(_id)).as(User.class);
	}

}