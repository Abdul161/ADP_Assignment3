/*
 * Abdul Aleem Chilwan
 * 220108447
 * Stakeholder.java
 * Creating Stakeholders
 * 9th June 2021
 */
package za.ac.cput.assignment3;

import java.io.Serializable;

/**
 *
 * @author AA
 */
public class Stakeholder implements Serializable{
    private String stHolderId;

    public Stakeholder() {
    }
    
    public Stakeholder(String stHolderId) {
        this.stHolderId = stHolderId;
    }
    
    public String getStHolderId() {
        return stHolderId;
    }

    public void setStHolderId(String stHolderId) {
        this.stHolderId = stHolderId;
    }

    @Override
    public String toString() {
       return stHolderId;
    }

}