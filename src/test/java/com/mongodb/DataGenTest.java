package com.mongodb;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class DataGenTest {

    private static String MONGO_URI = null;
    private static String DATABASE = null;
    private static String COLLECTION = null;
    private static boolean IS_DROP_COLLECTION = false;

    private static final String MONGO_URI_PROPERTY = "mongoClientURI";
    private static final String DATABASE_PROPERTY = "database";
    private static final String COLLECTION_PROPERTY = "collection";
    private static final String IS_DROP_COLLECTION_PROPERTY = "isDropCollection";


    /**
     *
     */
    @BeforeClass
    public static void beforeClass() {

        DataGenTest.MONGO_URI = System.getProperty(DataGenTest.MONGO_URI_PROPERTY);
        DataGenTest.DATABASE = System.getProperty(DataGenTest.DATABASE_PROPERTY);
        DataGenTest.COLLECTION = System.getProperty(DataGenTest.COLLECTION_PROPERTY);
        DataGenTest.IS_DROP_COLLECTION =
                Boolean.parseBoolean(System.getProperty(DataGenTest.IS_DROP_COLLECTION_PROPERTY));

    }

    /**
     *
     */
    @Test
    public void test() {
        DataGen dataGen
                = new DataGen(DataGenTest.MONGO_URI,
                              DataGenTest.DATABASE,
                              DataGenTest.COLLECTION,
                              DataGenTest.IS_DROP_COLLECTION);
    }

}
