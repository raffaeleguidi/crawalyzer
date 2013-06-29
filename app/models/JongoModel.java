package models;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.Oid;

import uk.co.panaxiom.playjongo.PlayJongo;

public abstract class JongoModel {

	protected static MongoCollection items() {
	    return null;
	}

	public ObjectId _id;

	public void insert() {
	    items().insert(this);
	}

	public void save() {
	    items().save(this);
	}
	public static JongoModel findById(String _id) {
		return items().findOne(Oid.withOid(_id)).as(JongoModel.class);
	}

}