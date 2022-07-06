package edu.msu.bensmana.randomly.Snippets.Models;

import org.simpleframework.xml.Attribute;

public class DogFact {
    /*
    API for this stopped working
     */
    @Attribute
    private String fact;

    public DogFact(String fact) {
        this.fact = fact;
    }

    public DogFact(){}

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }
}
