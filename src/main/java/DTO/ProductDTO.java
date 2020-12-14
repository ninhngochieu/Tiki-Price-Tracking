package DTO;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductDTO implements Comparable{
    private String id;
    private String name;
    private String image;
    private String id_item;
    private int price;
    private int review_count=0;
    private float rating_average;
    private JSONObject star;
    private int index = 99;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public float getRating_average() {
        return rating_average;
    }

    public void setRating_average(float rating_average) {
        this.rating_average = rating_average;
    }

    public JSONObject getStar() {
        return star;
    }

    public void setStar(JSONObject star) {
        this.star = star;
    }

    public ProductDTO(String id, String name, String image, String id_item, int price, int review_count, float rating_average, JSONObject star) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.id_item = id_item;
        this.price = price;
        this.review_count = review_count;
        this.rating_average = rating_average;
        this.star = star;
    }

    public ProductDTO() {
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    /*public ProductDTO(String id, String name, String image, String id_item, int price, int review_count) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.id_item = id_item;
        this.price = price;
        this.review_count = review_count;
    }*/

    public ProductDTO(String id, String name, String image, String id_item, int price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.id_item = id_item;
        this.price = price;
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

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", id_item='" + id_item + '\'' +
                ", price=" + price +
                ", review_count=" + review_count +
                ", rating_average=" + rating_average +
                ", star=" + star +
                ", index=" + index +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        ProductDTO p = (ProductDTO) o;
        if(index==((ProductDTO) o).index) return 0;
        else if(index>((ProductDTO) o).index) return 1;
        else return -1;
    }
}
