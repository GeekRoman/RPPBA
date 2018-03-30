package server;
import java.io.Serializable;

public class Cell implements Serializable{
    public String CellId;
    public String StorageId;
    public String Length;
    public String Height;
    public String Wight;
    public String Type;
    public String Status;
    public Cell(){}

    public Cell (String CellId, String StorageId, String Length,
                 String Height,String Wight, String Type,String Status){
        this.CellId = CellId;
        this.StorageId = StorageId;
        this.Length = Length;
        this.Height = Height;
        this.Wight = Wight;
        this.Type = Type;
        this.Status = Status;
    }

    public String getStatus() {
        return Status;
    }

    public String getWight() {
        return Wight;
    }

    public String getLength() {
        return Length;
    }

    public String getHeight() {
        return Height;
    }

    public String getCellId() {
        return CellId;
    }

    public String getType() {
        return Type;
    }

    public String getStorageId() {
        return StorageId;
    }
}
