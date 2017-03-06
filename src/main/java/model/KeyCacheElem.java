package model;

import DBControllers.DBKeyCacheElem;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 05.03.17.
 */
@DatabaseTable
public class KeyCacheElem {

    @DatabaseField(id = true)
    private String key;
    @DatabaseField
    private  String valueOfKey;

    public KeyCacheElem(){}




    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getValueOfKey() {
        return valueOfKey;
    }

    public void setValueOfKey(String valueOfKey) {
        this.valueOfKey = valueOfKey;
    }
}
