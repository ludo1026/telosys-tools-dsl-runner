/*
* Java bean class
* Created on 15 janv. 2015 ( Date ISO 2015-01-15 - Time 16:55:23 )
* Generated by Telosys Tools Generator ( version 2.1.1 )
*/

package src.main.java;

import java.io.Serializable;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity bean
 *
 * @author Telosys Tools Generator
 *
 */
@Document(collection = "employee")
public class Employee implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    private Integer    id           ; // Primary Key

    private Date       birthDate    ;
    private String     firstName    ;

    /**
     * Default constructor
     */
    public Employee()
    {
        super();
    }
    
    /**
     * Set the "id" field value
     * @param id
     */
    public void setId( Integer id )
    {
        this.id = id ;
    }
        
    /**
     * Get the "id" field value
     * @return the field value
     */
    public Integer getId()
    {
        return this.id;
    }
        
    /**
     * Set the "birthDate" field value
     * @param birthDate
     */
    public void setBirthDate( Date birthDate )
    {
        this.birthDate = birthDate;
    }
        
    /**
     * Get the "birthDate" field value
     * @return the field value
     */
    public Date getBirthDate()
    {
        return this.birthDate;
    }
        
    /**
     * Set the "firstName" field value
     * @param firstName
     */
    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }
        
    /**
     * Get the "firstName" field value
     * @return the field value
     */
    public String getFirstName()
    {
        return this.firstName;
    }
    
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(birthDate);
        sb.append("|");
        sb.append(firstName);
        sb.append("|");
        sb.append(id);
        return sb.toString(); 
    } 


}
