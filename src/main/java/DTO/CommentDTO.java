package DTO;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentDTO{
    private String id;
    private String title;
    private String content;
    private int thank_count;
    private float rating;
    private String id_product;
    private String full_name;
    public long purchased_at;

    public CommentDTO(String id, String title, String content, int thank_count, float rating, String id_product) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.thank_count = thank_count;
        this.rating = rating;
        this.id_product = id_product;
    }

    public CommentDTO() {

    }

    public CommentDTO(String id, String title, String content, int thank_count, float rating, String id_product, String full_name, long purchased_at) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.thank_count = thank_count;
        this.rating = rating;
        this.id_product = id_product;
        this.full_name = full_name;
        this.purchased_at = purchased_at;
    }


    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPurchased_at() {
     Date date = new Date(purchased_at*1000);
     Format format = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
     return format.format(date);
    }

    public void setPurchased_at(int purchased_at) {
        this.purchased_at = purchased_at;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", thank_count=" + thank_count +
                ", rating=" + rating +
                ", id_product='" + id_product + '\'' +
                '}';
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getThank_count() {
        return thank_count;
    }

    public void setThank_count(int thank_count) {
        this.thank_count = thank_count;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


}
