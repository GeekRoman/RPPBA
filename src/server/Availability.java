package server;
import java.io.Serializable;
public class Availability  implements Serializable{
    public String ItemId;
    public String Type;
    public String Length;
    public String Height;
    public String Wigth;
    public String Config;
    public String Color;
    public String Provider;
    public String Quantity;
    public String OrderQuantity;
    public String StorageId;
    public String CellId;
    Availability(){}


    public Availability (String ItemId,String Type,String Length,
                         String Height,String Wigth,String Config,
                         String Color,String Provider,String Quantity,
                         String OrderQuantity){
        this.ItemId = ItemId;
        this.Type = Type;
        this.Length = Length;
        this.Height = Height;
        this.Wigth = Wigth;
        this.Config = Config;
        this.Color = Color;
        this.Provider = Provider;
        this.Quantity = Quantity;
        this.OrderQuantity = OrderQuantity;
        this.StorageId = StorageId;
        this.CellId = CellId;
    }

    public Availability(String ItemId,String OrderQuantity,String CellId){
        this.ItemId = ItemId;
        this.OrderQuantity = OrderQuantity;
        this.CellId = CellId;
    }


    public String getType() {
        return Type;
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

    public String getWigth() {
        return Wigth;
    }

    public String getStorageId() {
        return StorageId;
    }
}
