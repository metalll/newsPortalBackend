package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 18.02.17.
 */
@DatabaseTable
public class Comments {
    public static final String YES = "YES";
    public static final String NO = "NO";
    //all size;

    public Comments(){}

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String annonymus;
    @DatabaseField
    private String parentID;
    @DatabaseField
    private String name;
    @DatabaseField
    private String email;
    @DatabaseField
    private String comment;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnonymus() {
        return annonymus;
    }

    public void setAnnonymus(String annonymus) {
        this.annonymus = annonymus;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
