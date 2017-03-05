package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 17.02.17.
 */
@DatabaseTable
public class News {

    public News(){}

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String type; //HOT||NEW
    @DatabaseField
    private String image;
    @DatabaseField
    private String numberOfViewed;
    @DatabaseField
    private String headerDecription;
    @DatabaseField
    private String minimalDescription;
    @DatabaseField
    private String content;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNumberOfViewed() {
        return numberOfViewed;
    }

    public void setNumberOfViewed(String numberOfViewed) {
        this.numberOfViewed = numberOfViewed;
    }

    public String getHeaderDecription() {
        return headerDecription;
    }

    public void setHeaderDecription(String headerDecription) {
        this.headerDecription = headerDecription;
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
