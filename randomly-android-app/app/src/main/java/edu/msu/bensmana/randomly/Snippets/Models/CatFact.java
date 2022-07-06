package edu.msu.bensmana.randomly.Snippets.Models;

import org.simpleframework.xml.Attribute;

public class CatFact {
    @Attribute
    private String[] data;

    public CatFact(String[] data) {
        this.data = data;
    }

    public CatFact(){}

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
