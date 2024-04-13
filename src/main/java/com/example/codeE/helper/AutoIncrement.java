package com.example.codeE.helper;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class AutoIncrement {

    private final MongoCollection<Document> countersCollection;

    public AutoIncrement(MongoDatabase database) {
        this.countersCollection = database.getCollection("code_submission");
    }

    public int getNextSequence(String collectionName) {
        Document counter = countersCollection.findOneAndUpdate(
                Filters.eq("_id", collectionName),
                Updates.inc("sequence_value", 1)
        );

        if (counter == null) {
            // If the counter document doesn't exist, create it
            counter = new Document("_id", collectionName)
                    .append("sequence_value", 1);
            countersCollection.insertOne(counter);
            return 1;
        } else {
            return counter.getInteger("sequence_value") + 1;
        }
    }
}
