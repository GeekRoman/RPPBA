package server;
import java.io.Serializable;
public class Availability  implements Serializable{
    public String ItemId;
    public String Name;
    public String Length;
    public String Height;
    public String Wight;
    public String Config;
    public String Color;
    public String Provider;
    public String Quantity;
    public String OrderQuantity;
    public String StorageId;
    public String CellId;
    Availability(){}


    public Availability (String ItemId,String Name,String Length,
                         String Height,String Wight,String Config,
                         String Color,String Provider,String Quantity,
                         String OrderQuantity,String StorageId,String CellId){
        this.ItemId = ItemId;
        this.Name = Name;
        this.Length = Length;
        this.Height = Height;
        this.Wight = Wight;
        this.Config = Config;
        this.Color = Color;
        this.Provider = Provider;
        this.Quantity = Quantity;
        this.OrderQuantity = OrderQuantity;
        this.StorageId = StorageId;
        this.CellId = CellId;
    }

    public Availability(String ItemId,String OrderQuantity){
        this.ItemId = ItemId;
        this.OrderQuantity = OrderQuantity;
    }


    public String getName() {
        return Name;
    }

    public String getCellId() {
        return CellId;
    }

    public String getColor() {
        return Color;
    }

    public String getConfig() {
        return Config;
    }

    public String getHeight() {
        return Height;
    }

    public String getItemId() {
        return ItemId;
    }

    public String getLength() {
        return Length;
    }

    public String getOrderQuantity() {
        return OrderQuantity;
    }

    public String getProvider() {
        return Provider;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getWight() {
        return Wight;
    }
    public String getStorageId() {
        return StorageId;
    }
}
