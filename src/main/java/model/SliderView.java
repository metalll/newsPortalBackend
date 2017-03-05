package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 18.02.17.
 */

@DatabaseTable
public class SliderView {

    public SliderView(){}

    @DatabaseField(generatedId = true)
    private String id ;
    @DatabaseField
    private String text;
    @DatabaseField
    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
