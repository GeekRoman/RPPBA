package server;
import java.io.Serializable;

public class Storage implements Serializable{
    public String StorageId;
    public String Address;
    public String Status;
    public Storage(){}

    public Storage (String StorageId,String Address,String Status){
        this.StorageId = StorageId;
        this.Address = Address;
        this.Status = Status;
    }

    public Storage(String StorageId,String Status){
        this.StorageId = StorageId;
        this.Status = Status;
    }
    public String getStatus() {
        return Status;
    }

    public String getAddress() {
        return Address;
    }

    public String getStorageId() {
        return StorageId;
    }
}
