package p42.androidbooksclient.model;

public class Tag {
    private Integer _id = null;
    private String _name = null;

    public Tag(Integer id, String name){
        this._id = id;
        this._name = name;
    }

    public Integer getID(){
        return this._id;
    }

    public void setID(Integer id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }
}
