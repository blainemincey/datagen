package com.mongodb;


import java.util.Arrays;

/**
 * Created by bmincey on 6/30/17.
 */
public class MyTestDocument {

    // fields
    private String applID;
    private String hqSKU;
    private String fieldSKU;
    private String longDescription;
    private String make;
    private String model;
    private int year;
    private Integer[] DCID;
    private String[] comments;


    /**
     *
     */
    public MyTestDocument() {

        this.setApplID(RandomDataGenerator.getWords(1));
        this.setHqSKU(RandomDataGenerator.getWords(1));
        this.setFieldSKU(RandomDataGenerator.getWords(1));
        this.setLongDescription(RandomDataGenerator.getWords(5));
        this.setMake(RandomDataGenerator.getWords(1));
        this.setModel(RandomDataGenerator.getWords(1));
        this.setYear(RandomDataGenerator.getRandomYear(1880, 2018));
        this.setDCID(RandomDataGenerator.getRandomIntArray(10));
        this.setComments(RandomDataGenerator.getStringArray(10));
    }

    /**
     *
     * @return
     */
    public String getApplID() {
        return applID;
    }

    /**
     *
     * @param applID
     */
    public void setApplID(String applID) {
        this.applID = applID;
    }

    /**
     *
     * @return
     */
    public String getHqSKU() {
        return hqSKU;
    }

    /**
     *
     * @param hqSKU
     */
    public void setHqSKU(String hqSKU) {
        this.hqSKU = hqSKU;
    }

    /**
     *
     * @return
     */
    public String getFieldSKU() {
        return fieldSKU;
    }

    /**
     *
     * @param fieldSKU
     */
    public void setFieldSKU(String fieldSKU) {
        this.fieldSKU = fieldSKU;
    }

    /**
     *
     * @return
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     *
     * @param longDescription
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     *
     * @return
     */
    public String getMake() {
        return make;
    }

    /**
     *
     * @param make
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     *
     * @return
     */
    public String getModel() {
        return model;
    }

    /**
     *
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     *
     * @return
     */
    public Integer[] getDCID() {
        return DCID;
    }

    /**
     *
     * @param DCID
     */
    public void setDCID(Integer[] DCID) {
        this.DCID = DCID;
    }

    /**
     *
     * @return
     */
    public String[] getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     */
    public void setComments(String[] comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "MyTestDocument{" +
                "applID='" + applID + '\'' +
                ", hqSKU='" + hqSKU + '\'' +
                ", fieldSKU='" + fieldSKU + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", DCID=" + Arrays.toString(DCID) +
                ", comments=" + Arrays.toString(comments) +
                '}';
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new MyTestDocument());
    }
}
