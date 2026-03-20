package p42.androidbooksclient.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private Integer _id = null;
    private String _title = null;
    private Integer _publicationYear = null;
    private Integer _authorID = null;
    private ArrayList<Tag> _tags = null;

    public Book(Integer id, String title, Integer publicationYear, Integer authorID){
        this._id = id;
        this._title = title;
        this._publicationYear = publicationYear;
        this._authorID = authorID;
    }

    public Integer getID(){
        return this._id;
    }

    public void setID(Integer id){
        this._id = id;
    }

    public String getTitle(){
        return this._title;
    }

    public void setTitle(String title){
        this._title = title;
    }

    public Integer getPublicationYear(){
        return this._publicationYear;
    }

    public void setPublicationYear(Integer publicationYear){
        this._publicationYear = publicationYear;
    }

    public Integer getAuthorID(){
        return this._authorID;
    }

    public void setAuthorID(Integer authorID){
        this._authorID = authorID;
    }

    public ArrayList<Tag> getTags(){
        return this._tags;
    }

    public void setTags(List<Tag> tags){
        this._tags = new ArrayList<>(tags);
    }

    public void addTag(Tag tag){
        if(this._tags == null){
            this._tags = new ArrayList<>();
        }
        this._tags.add(tag);
    }
}
