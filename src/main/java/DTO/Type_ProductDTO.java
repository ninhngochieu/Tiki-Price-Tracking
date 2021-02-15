package DTO;

public class Type_ProductDTO {
    String id;
    String id_items;

    @Override
    public String toString() {
        return "Type_ProductDTO{" +
                "id='" + id + '\'' +
                ", id_items='" + id_items + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_items() {
        return id_items;
    }

    public void setId_items(String id_items) {
        this.id_items = id_items;
    }

    public Type_ProductDTO(String id, String id_items) {
        this.id = id;
        this.id_items = id_items;
    }
}
