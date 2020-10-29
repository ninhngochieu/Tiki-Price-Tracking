package DTO;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        Date date=new Date(last_update.getTime());
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        this.last_update = cal.getTime();

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
