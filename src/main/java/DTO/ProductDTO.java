package DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductDTO implements Serializable{
    private String id;
    private String name;
    private String image;
    private String id_item;
    private int price;
    private int review_count=0;

    public ProductDTO() {
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public ProductDTO(String id, String name, String image, String id_item, int price, int review_count) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.id_item = id_item;
        this.price = price;
        this.review_count = review_count;
    }

    public ProductDTO(String id, String name, String image, String id_item, int price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.id_item = id_item;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", id_item='" + id_item + '\'' +
                ", price=" + price +
                '}';
    }

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item = id_item;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
