package edu.msu.bensmana.randomly.Snippets.Models;

import org.simpleframework.xml.Attribute;

public class DogFact2 {
    /*
    API for original dog fact functionality was shut down
     */
    @Attribute
    private String[] facts;

    @Attribute
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DogFact2(String[] facts, String status) {
        this.facts = facts;
        this.status = status;
    }

    public DogFact2(){}

    public String[] getFacts() {
        return facts;
    }

    public void setFacts(String[] facts) {
        this.facts = facts;
    }
}
