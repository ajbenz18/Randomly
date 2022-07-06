package edu.msu.bensmana.randomly.Snippets.Models;

import org.simpleframework.xml.Attribute;

public class Kanye {
    @Attribute
    private String quote;

    public Kanye(){}

    public Kanye(String q){
        quote = q;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
