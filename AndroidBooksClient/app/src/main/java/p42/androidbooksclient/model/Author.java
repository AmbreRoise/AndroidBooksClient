package p42.androidbooksclient.model;

import java.util.ArrayList;

public class Author {
    private Integer _id = null;
    private String _firstname = null;
    private String _lastname = null;
    private ArrayList<Book> _books = null;

    public Author(Integer id, String firstname, String lastname){
        this._id = id;
        this._firstname = firstname;
        this._lastname = lastname;
    }

    public Integer getID(){
        return this._id;
    }

    public void setID(Integer id){
        this._id = id;
    }

    public String getFirstname() {
        return this._firstname;
    }

    public void setFirstname(String firstname){
        this._firstname = firstname;
    }

    public String getLastname(){
        return this._lastname;
    }

    public void setLastname(String lastname){
        this._lastname = lastname;
    }

    public ArrayList<Book> getBooks(){
        return this._books;
    }

    public void addBook(Book book){
        if(this._books == null){
            this._books = new ArrayList<>();
        }
        this._books.add(book);
    }
}
