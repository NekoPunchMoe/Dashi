package offline;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoDatabase;

import db.DBUtil;

public class AverageRating {
	
	private static final String ITEM_ID = "0634029363";
	private static final String COLLECTION_NAME = "ratings";
	private static final String USER_COLUMN = "user";
	private static final String ITEM_COLUMN = "item";
	private static final String RATING_COLUMN = "rating";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase(DBUtil.DB_NAME);
		StringBuilder sb = new StringBuilder();
		sb.append("function(){");
		sb.append("emit(this.item, this.rating);}");
		String map = sb.toString();
		String reduce = "function(key, values) {return Array.avg(values)} ";
		MapReduceIterable<Document> results = db.getCollection(COLLECTION_NAME)
				.mapReduce(map, reduce);
		results.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				String id = document.getString("_id");
				Double value = document.getDouble("value");
				System.out.println("id:" + id + "value:" + value);	
			}
		});
	}

}
