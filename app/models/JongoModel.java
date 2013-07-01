package models;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.Oid;

import com.mongodb.MongoException.DuplicateKey;

import uk.co.panaxiom.playjongo.PlayJongo;

public abstract class JongoModel {

	public ObjectId _id;


}