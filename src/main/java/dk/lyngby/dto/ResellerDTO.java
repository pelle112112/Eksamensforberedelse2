package dk.lyngby.dto;

public class ResellerDTO {
    public int id;
    public String name;
    public String address;
    public String phone;

    public ResellerDTO(int id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    public ResellerDTO() {
    }
}
