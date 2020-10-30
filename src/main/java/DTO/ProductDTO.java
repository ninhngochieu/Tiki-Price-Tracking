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
    private float rating_average;
    private float star1;
    private float star2;
    private float star3;
    private float star4;
    private float star5;

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
                ", star1=" + star1 +
                ", star2=" + star2 +
                ", star3=" + star3 +
                ", star4=" + star4 +
                ", star5=" + star5 +
                '}';
    }

    public ProductDTO(String id, String name, String image, String id_item, int price, int review_count, float rating_average, float star1, float star2, float star3, float star4, float star5) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.id_item = id_item;
        this.price = price;
        this.review_count = review_count;
        this.rating_average = rating_average;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.star5 = star5;
    }

    public float getRating_average() {
        return rating_average;
    }

    public void setRating_average(float rating_average) {
        this.rating_average = rating_average;
    }

    public float getStar1() {
        return star1;
    }

    public void setStar1(float star1) {
        this.star1 = star1;
    }

    public float getStar2() {
        return star2;
    }

    public void setStar2(float star2) {
        this.star2 = star2;
    }

    public float getStar3() {
        return star3;
    }

    public void setStar3(float star3) {
        this.star3 = star3;
    }

    public float getStar4() {
        return star4;
    }

    public void setStar4(float star4) {
        this.star4 = star4;
    }

    public float getStar5() {
        return star5;
    }

    public void setStar5(float star5) {
        this.star5 = star5;
    }

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
