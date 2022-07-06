package edu.msu.bensmana.randomly.Snippets.Models;

import org.simpleframework.xml.Attribute;

public class Quote {
    @Attribute
    private String _id;

    @Attribute
    String[] tags;

    @Attribute
    private String content;

    @Attribute
    private String author;

    @Attribute
    private String authorSlug;


    @Attribute
    private String length;

    @Attribute
    private String dateAdded;

    @Attribute
    private String dateModified;

    public Quote() {
    }


    public Quote(String _id, String[] tags, String content, String author, String authorSlug, String length, String dateAdded, String dateModified) {
        this._id = _id;
        this.tags = tags;
        this.content = content;
        this.author = author;
        this.authorSlug = authorSlug;
        this.length = length;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;


    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorSlug() {
        return authorSlug;
    }

    public void setAuthorSlug(String authorSlug) {
        this.authorSlug = authorSlug;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}
