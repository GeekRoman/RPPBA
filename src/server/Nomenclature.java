package server;

import java.io.Serializable;

public class Nomenclature implements Serializable{
    private String ItemId;
    private String Name;
    private String Length;
    private String Height;
    private String Width;
    private String Config;
    private String Provider;

    public Nomenclature(){}

    // Set_products
    public Nomenclature(String ItemId,String Name){
        this.ItemId = ItemId;
        this.Name = Name;
    }

    public String getItemId() {
        return ItemId;
    }

    public String getName() {
        return Name;
    }
}
