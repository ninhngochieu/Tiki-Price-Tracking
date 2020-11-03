package DTO;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HistoryDTO {
    private String id;
    private String id_product;
    private Timestamp last_update;
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

    public String getLast_update() {
        String d = last_update.toString().split(" ")[0];
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(d);
            return date.getDay()+"-"+date.getMonth()+"-"+date.getYear();
        } catch (ParseException e) {
            e.printStackTrace();
            return last_update.toString();
        }
    }

    public void setLast_update(Timestamp last_update) {
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

    public HistoryDTO(String id, String id_product, Timestamp last_update, int current_price) {
        this.id = id;
        this.id_product = id_product;
        this.last_update = last_update;
        this.current_price = current_price;
    }
}
