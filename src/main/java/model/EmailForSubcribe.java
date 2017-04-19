package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 18.04.17.
 */


@DatabaseTable
public class EmailForSubcribe {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String email;


    public EmailForSubcribe(){}



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
