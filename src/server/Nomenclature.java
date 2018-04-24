package server;

import java.io.Serializable;

public class Nomenclature implements Serializable{
    private String ItemId;
    private String Type;
    private String Length;
    private String Height;
    private String Width;
    private String Config;
    private String Provider;

    public Nomenclature(){}

    // Set_products
    public Nomenclature(String ItemId,String Type){
        this.ItemId = ItemId;
        this.Type = Type;
    }

    // Set_products,Get_products
    public Nomenclature(String ItemId,String Type,String Length,String Height,String Width,String Config,String Provider) {
        this.ItemId = ItemId;
        this.Type = Type;
        this.Length = Length;
        this.Height = Height;
        this.Width = Width;
        this.Config = Config;
        this.Provider = Provider;
    }

    public String getItemId() {
        return ItemId;
    }

    public String getType() {
        return Type;
    }

    public String getConfig() {
        return Config;
    }

    public String getHeight() {
        return Height;
    }

    public String getLength() {
        return Length;
    }

    public String getProvider() {
        return Provider;
    }

    public String getWidth() {
        return Width;
    }
}
