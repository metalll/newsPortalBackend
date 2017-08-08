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
    private String dataOfAdded;

    @DatabaseField
    private String image;
    @DatabaseField
    private String numberOfViewed;
    @DatabaseField
    private String headerDescription;
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

    public String getHeaderDescription() {
        return headerDescription;
    }

    public void setHeaderDescription(String headerDescription) {
        this.headerDescription = headerDescription;
    }

    public String getDataOfAdded() {
        return dataOfAdded;
    }

    public void setDataOfAdded(String dataOfAdded) {
        this.dataOfAdded = dataOfAdded;
    }
}
