/*
* Java bean class
* Created on 25 mars 2015 ( Date ISO 2015-03-25 - Time 17:26:45 )
* Generated by Telosys Tools Generator ( version 2.1.1 )
*/

package org.demo.repository;


import com.mongodb.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.demo.bean.Country;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Repository
 *
 * @author Telosys Tools Generator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CountryRepositoryTest
{

    /**
     * Spring Data Repository
     */
    @Autowired
    CountryRepository countryRepository;

    /**
     * MongoDB Java client
     */
    @Autowired
    Mongo mongo;

    /**
     * Initialize MongoDB with Java client
     */
    @Before
    public void setUp() throws Exception {

        // Drop database
        mongo.dropDatabase("test");

        // Create database
        DB db = mongo.getDB("test");

        // Documents collection
        DBCollection collection = db.getCollection("country");
    }

    /**
     * Spring Data : Create and search Country in MongoDB
     */
    @Test
    public void test() {
    
        Country country = new Country();
        country.setLabel("Test");
        country.setCode("Test");
        countryRepository.save(country);
        
        Iterable<Country> countrys = countryRepository.findAll();
    
        System.out.println("Countrys :");
        for(Country country2 : countrys) {
            System.out.println(country2);
        }

    }

    /**
     * Clean MongoDB with Java client
     */
    @After
    public void after() {

        // Create database
        DB db = mongo.getDB("test");
    
        // Documents collection
        DBCollection collection = db.getCollection("country");
    
        // Results
        System.out.println("\nRÃ©sultat:");
        DBCursor cursor = collection.find();
        if(cursor.count() == 0) {
            System.out.println("<vide>");
        } else {
            while(cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        }
        cursor.close();

        // Drop database
        mongo.dropDatabase("test");
    }

}
