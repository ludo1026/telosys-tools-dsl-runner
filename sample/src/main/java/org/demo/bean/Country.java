/*
 * Java bean class
 * Created on 26 mars 2015 ( Date ISO 2015-03-26 - Time 17:42:04 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package org.demo.bean;

import java.io.Serializable;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity bean
 *
 * @author Telosys Tools Generator
 *
 */
@Document(collection = "countrys")
public class Country implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String label;
    @Id
    private String code;

    /**
     * Default constructor
     */
    public Country()
    {
        super();
    }
    
    /**
     * Set the "label" field value
     * @param label
     */
    public void setLabel( String label )
    {
        this.label = label;
    }
        
    /**
     * Get the "label" field value
     * @return the field value
     */
    public String getLabel()
    {
        return this.label;
    }
        
    /**
     * Set the "code" field value
     * @param code
     */
    public void setCode( String code )
    {
        this.code = code;
    }
        
    /**
     * Get the "code" field value
     * @return the field value
     */
    public String getCode()
    {
        return this.code;
    }
    
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(label);
        sb.append("|");
        sb.append(code);
        return sb.toString(); 
    } 


}
