/*
 * Java bean class
 * Created on 30 mars 2015 ( Date ISO 2015-03-30 - Time 14:41:59 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package org.demo.dao;

import java.util.Date;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.demo.bean.Employee;
import org.demo.dao.EmployeeDao;

/**
 * Test : DAO : Employee
 *
 * @author Telosys Tools Generator
 *
 */
public class EmployeeDaoTest {

    String HOST = "localhost";
    Integer PORT = 12345;
    String DATABASE = "test";

    EmployeeDao employeeDao = new EmployeeDao(HOST, PORT, DATABASE);

    /**
     * Mongo in memory
     */
    MongodExecutable mongodExecutable;

    /**
     * Initialize MongoDB with Java client
     */
    @Before
    public void setUp() throws Exception {

        MongodStarter starter = MongodStarter.getDefaultInstance();

        IMongodConfig mongodConfig = new MongodConfigBuilder()
            .version(Version.Main.DEVELOPMENT)
            .net(new Net(PORT, Network.localhostIsIPv6()))
            .build();

        this.mongodExecutable = starter.prepare(mongodConfig);
        this.mongodExecutable.start();
    }

    @After
    public void after() {
        if (this.mongodExecutable != null) {
            this.mongodExecutable.stop();
        }
    }

    @Test
    public void test() {
        // Given
        Employee employee = new Employee();
        employee.setFirstName("firstName");
        employee.setId(1);

        // Save
        employeeDao.save(employee);

        // Find by id
        Employee employeeSaved = employeeDao.findById(employee.getId());

        // Then
        Assert.assertEquals("firstName", employeeSaved.getFirstName());
        Assert.assertEquals(Integer.valueOf(1), employeeSaved.getId());

        // Delete
        employeeDao.remove(employeeSaved);

        // Find by id
        Employee employeeDeleted = employeeDao.findById(employee.getId());

        // Then
        Assert.assertNull(employeeDeleted);
    }

}
