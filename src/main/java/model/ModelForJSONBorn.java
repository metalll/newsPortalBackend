package model;

import java.util.List;

/**
 * Created by NSD on 05.03.17.
 */
public class ModelForJSONBorn<T> {


    public ModelForJSONBorn(List<T> values,String elementsCount){
        this.values = values;
        this.elementsCount = elementsCount;
    }


    public ModelForJSONBorn(List<T> values,int elementsCount){
        this.values = values;
        this.elementsCount = String.valueOf(elementsCount);
    }


    public List<T> values ;
    public String elementsCount;

}
