package models;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class JongoModel {

	public ObjectId _id;

	static MongoCollection items() {throw new NotImplementedException();};
}