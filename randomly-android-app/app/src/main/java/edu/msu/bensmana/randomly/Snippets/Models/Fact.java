package edu.msu.bensmana.randomly.Snippets.Models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "fact")
public class Fact {
    @Attribute
    private String id;

    @Attribute
    private String text;

    @Attribute
    private String source;

    @Attribute
    private  String source_url;

    @Attribute
    private String language;

    @Attribute
    private String permalink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Fact(){}

    public Fact(String id, String text, String source, String source_url, String language, String permalink){
        this.id = id;
        this.text = text;
        this.source = source;
        this.source_url = source_url;
        this.language = language;
        this.permalink = permalink;
    }
}
