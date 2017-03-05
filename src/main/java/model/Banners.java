package model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 18.02.17.
 */
@DatabaseTable
public class Banners {

    public Banners(){}

    //get count
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String image;
    @DatabaseField
    private String link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


//    IndustryController
//    get + query industry
//    post
//    delete + query // nothing
//    put update id + filed name // update
}
