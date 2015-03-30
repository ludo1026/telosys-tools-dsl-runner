/*
* Java bean class
* Created on 27 mars 2015 ( Date ISO 2015-03-27 - Time 12:03:45 )
* Generated by Telosys Tools Generator ( version 2.1.1 )
*/

package org.demo.repository;

import java.util.Date;

import com.mongodb.*;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import org.demo.ApplicationConfigTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.demo.bean.Employee;

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
@SpringApplicationConfiguration(classes = ApplicationConfigTest.class)
public class EmployeeRepositoryTest
{

    /**
     * Spring Data Repository
     */
    @Autowired
    EmployeeRepository employeeRepository;

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
        DBCollection collection = db.getCollection("employees");
    }

    /**
     * Spring Data : Create and search Employee in MongoDB
     */
    @Test
    public void test() {
    
        Employee employee = new Employee();
        employee.setFirstName("Test");
        employee.setId(1);

        employeeRepository.save(employee);
        
        Iterable<Employee> employees = employeeRepository.findAll();
    
        System.out.println("Employees :");
        for(Employee employee2 : employees) {
            System.out.println(employee2);
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
        DBCollection collection = db.getCollection("employees");
    
        // Results
        System.out.println("\nResult:");
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
