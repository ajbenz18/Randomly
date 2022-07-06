package edu.msu.bensmana.randomly;

/*
* Class to store a contact object
 */
public class Contact implements Comparable<Contact>{
    // persons name
    private String number;

    // persons phone number
    private String name;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact(String num, String n){
        number = num;
        name = n;

    }

    @Override
    public int compareTo(Contact other){
        return name.compareTo(other.getName());
    }

}
