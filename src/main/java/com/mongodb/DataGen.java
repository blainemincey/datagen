package com.mongodb;


import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;
import org.apache.commons.lang3.time.StopWatch;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
/**
 * Created by bmincey on 6/30/17.
 */
public class DataGen {

    private String DATABASE_NAME = null;
    private String COLLECTION_NAME = null;
    private String MONGODB_URI = null;

    private boolean isDropCollection = false;

    private MongoDatabase mongoDatabase = null;

    private static Logger LOG = Logger.getLogger(DataGen.class.getName());

    /**
     *
     */
    public DataGen() {
        LOG.info("DataGen initializing with default constructor...");

        this.DATABASE_NAME = "loadData";
        this.COLLECTION_NAME = "myCollection";
        this.MONGODB_URI = "mongodb://localhost:27017";

        this.dbInit();
        this.generateData();
    }

    /**
     *
     * @param mongodbURI
     * @param databaseName
     * @param collectionName
     */
    public DataGen(String mongodbURI, String databaseName, String collectionName, boolean isDropCollection) {
        LOG.info("DataGen initializing with overloaded constructor...");

        this.MONGODB_URI = mongodbURI;
        this.DATABASE_NAME = databaseName;
        this.COLLECTION_NAME = collectionName;
        this.isDropCollection = isDropCollection;

        LOG.info("Connecting using URI: " + this.MONGODB_URI + ", Database: " + this.DATABASE_NAME +
                        ", Collection: " + this.COLLECTION_NAME);

        this.dbInit();
        this.generateData();
    }

    /**
     *
     */
    private void dbInit() {

        LOG.info("Init database...");
        MongoClientURI uri = new MongoClientURI(this.MONGODB_URI);

        MongoClient mongoClient = new MongoClient(uri);

        this.mongoDatabase = mongoClient.getDatabase(this.DATABASE_NAME);
    }

    /**
     *
     */
    private void generateData() {

        MongoCollection<Document> mongoCollection = this.mongoDatabase.getCollection(this.COLLECTION_NAME);


        // drop the collection if it already exists
        if(this.isDropCollection && this.collectionExists(this.COLLECTION_NAME)) {
            LOG.info("Collection exists.  Dropping collection...");
            mongoCollection.drop();
            mongoCollection = this.mongoDatabase.getCollection(this.COLLECTION_NAME);
        }

        int totalRecords = 0;

        // Timer for million
        StopWatch millionTime = new StopWatch();
        millionTime.start();

        // Loop for each 100,000 records to generate 1 million records
        for(int i = 0; i < 10; i++) {

            // Initialize Bulk Write
            List<WriteModel<Document>> writes = new ArrayList<WriteModel<Document>>();

            // generate 100000 records
            for (int j = 0; j < 100000; j++) {

                // test document to generate data
                MyTestDocument myTestDocument = new MyTestDocument();

                // convert int array to BasicDBList
                BasicDBList basicDBList = new BasicDBList();
                Integer[] myDCIDArray = myTestDocument.getDCID();
                for(int k = 0; k < myDCIDArray.length; k++) {
                    basicDBList.add(myDCIDArray[k]);
                }

                // convert string array to BasicDBList
                BasicDBList basicDBList2 = new BasicDBList();
                String[] myCommentsArray = myTestDocument.getComments();
                for(int l = 0; l < myCommentsArray.length; l++) {
                    basicDBList2.add(myCommentsArray[l]);
                }

                // add 100,000 test documents to write array as InsertOneModel
                writes.add(
                        new InsertOneModel<Document>(
                                new Document("applID", myTestDocument.getApplID())
                                        .append("hqSKU", myTestDocument.getHqSKU())
                                        .append("fieldSKU", myTestDocument.getFieldSKU())
                                        .append("longDescription", myTestDocument.getLongDescription())
                                        .append("make", myTestDocument.getMake())
                                        .append("model", myTestDocument.getModel())
                                        .append("year", myTestDocument.getYear())
                                        .append("dcid", basicDBList)
                                        .append("comments", basicDBList2)
                                        .append("createDate", myTestDocument.getCreateDate())
                                        .append("myDecimal128", myTestDocument.getMyDecimal128())));


            }

            // bulk write operation
            BulkWriteResult bulkWriteResult = mongoCollection.bulkWrite(writes,new BulkWriteOptions().ordered(true));

            // add the total records up and display
            totalRecords += bulkWriteResult.getInsertedCount();
            LOG.info("Total inserts: " + totalRecords);
        }

        LOG.info("Time for a million records inserted: " + millionTime.toString());
    }

    /**
     *
     * @param collectionName
     * @return
     */
    public boolean collectionExists(String collectionName) {

        if (this.mongoDatabase.listCollectionNames().into(new ArrayList<String>()).contains(collectionName)) {
            return true;
        }
        else {
            return false;
        }

    }


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("DataGen starting...");
        StopWatch totalTime = new StopWatch();
        totalTime.start();

        new DataGen();

        System.out.println("DataGen complete.");
        System.out.println("TotalTime: " + totalTime.toString());
    }
}
