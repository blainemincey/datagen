package com.mongodb;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.util.GregorianCalendar;

/**
 * Created by bmincey on 6/30/17.
 */
public class RandomDataGenerator {


    /**
     *
     * @param numWords
     * @return
     */
    public static String getWords(int numWords) {
        Lorem lorem = LoremIpsum.getInstance();

        return lorem.getWords(numWords);
    }

    /**
     *
     * @param arrayLength
     * @return
     */
    public static String[] getStringArray(int arrayLength) {
        Lorem lorem = LoremIpsum.getInstance();
        String[] randomString = new String[arrayLength];

        for(int i = 0; i < arrayLength; i++) {
            randomString[i] = lorem.getWords(1);
        }

        return randomString;
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public static int getRandomYear(int start, int end) {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randomDateBetween(start, end);

        return year;
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    private static int randomDateBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    /**
     *
     * @param arrayLength
     * @return
     */
    public static Integer[] getRandomIntArray(int arrayLength) {
        Integer[] list = new Integer[arrayLength];
        for(int i = 0; i < arrayLength; i++) {
            int n = (int)(Math.random() * 100 + 1);
            list[i] = n;
        }

        return list;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Start: " + new java.util.Date());

        int year = RandomDataGenerator.getRandomYear(1880,2018);

        System.out.println("The year " + year + " is between 1880 and 2018");

        Integer[] randomIntArray = RandomDataGenerator.getRandomIntArray(10);
        for(int i = 0; i < randomIntArray.length; i++) {
            System.out.println("element " + i + " is " + randomIntArray[i]);
        }

        System.out.println("Get Words: " + RandomDataGenerator.getWords(5));

        String[] stringArray = RandomDataGenerator.getStringArray(5);
        for(int j = 0; j < stringArray.length; j++) {
            System.out.println("Element: " + j + " is " + stringArray[j]);
        }


        System.out.println("End: " + new java.util.Date());
    }
}
