package DTO;

import java.util.Date;

public class HistoryDTO {
    private String id;
    private String id_product;
    private Date last_update;
    private int current_price;

    @Override
    public String toString() {
        return "HistoryDTO{" +
                "id='" + id + '\'' +
                ", id_product='" + id_product + '\'' +
                ", last_update=" + last_update +
                ", current_price=" + current_price +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public int getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(int current_price) {
        this.current_price = current_price;
    }

    public HistoryDTO() {
    }

    public HistoryDTO(String id, String id_product, Date last_update, int current_price) {
        this.id = id;
        this.id_product = id_product;
        this.last_update = last_update;
        this.current_price = current_price;
    }
}
