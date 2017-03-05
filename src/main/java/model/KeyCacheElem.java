package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 05.03.17.
 */
@DatabaseTable
public class KeyCacheElem {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String key;
    @DatabaseField
    private  String value;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
