package android.example.com.sqliteapp.model;

public class Data {
    private String id;
    private String name;
    private String address;

    public Data() {
    }

    public Data(String id, String name, String address) {
        this.id=id;
        this.name=name;
        this.address=address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

}

