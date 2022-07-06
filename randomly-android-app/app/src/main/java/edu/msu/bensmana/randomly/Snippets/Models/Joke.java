package edu.msu.bensmana.randomly.Snippets.Models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "joke")
public class Joke {
    @Attribute
    private String id;

    @Attribute
    private String joke;

    public String getJoke() {
        return joke;
    }

    public void setText(String text) {
        this.joke = text;
    }

    @Attribute
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Joke() {}

    public Joke(String t, String i, int s){
        this.joke = t;
        this.id = i;
        this.status = s;
    }
}
