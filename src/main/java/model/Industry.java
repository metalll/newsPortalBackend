package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 17.02.17.
 */
@DatabaseTable
public class Industry {

    public Industry(){}

    //get size;
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String image;
    @DatabaseField
    private String headerText;
    @DatabaseField
    private String minimalDescription;
    @DatabaseField
    private String content; //html;

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

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getMinimalDescription() {
        return minimalDescription;
    }

    public void setMinimalDescription(String minimalDescription) {
        this.minimalDescription = minimalDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
