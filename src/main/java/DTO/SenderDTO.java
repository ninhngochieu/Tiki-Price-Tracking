package DTO;

import java.util.ArrayList;
import java.util.HashMap;

public class SenderDTO extends HashMap<String,Object>{
    private Object data;
    private Object products;
    private String key;
    private boolean status = true;
    private int min_price;
    private int max_price;

    private int total = 0;
    private int per_page = 0;
    private int current_page = 0;
    private int last_page = 0;

    public SenderDTO(Object data, boolean status, int total, int per_page, int current_page, int last_page) {
        this.data = data;
        this.status = status;
        this.total = total;
        this.per_page = per_page;
        this.current_page = current_page;
        this.last_page = last_page;
    //Send data with paging
        this.put("data",data);
        this.put("status",status);
        this.put("total",total);
        this.put("per_page",per_page);
        this.put("current_page",current_page);
        this.put("last_page",last_page);
    }

    public SenderDTO(Object data, boolean status, int min_price, int max_price) {//Send data and max-min price//4 p
        this.data = data;
        this.status = status;
        this.min_price = min_price;
        this.max_price = max_price;

        this.put("data",data);
        this.put("status",status);
        this.put("min_price",min_price);
        this.put("max_price",max_price);
    }

    public SenderDTO(Object data, String key, ArrayList<String> products) {//Send data with public key//3 param
        this.data = data;
        this.key = key;
        //this.status = status;
        this.products = products;

        this.put("name",products);
        this.put("data",data);
        this.put("key",key);
        //this.put("status",status);

    }

    public SenderDTO(Object data, boolean status) {//Send data//2 param
        this.data = data;
        this.status = status;

        this.put("data",data);
        this.put("status",status);
    }

    public SenderDTO() {
    }

    public Object getData() {
        return data;
    }

    public HashMap setData(ArrayList<Object> data) {
        this.data = data;
        this.put("data",data);
        return this;
    }

    public String getKey() {
        return key;
    }

    public HashMap setKey(String key) {
        this.key = key;
        this.put("key",key);
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public HashMap setStatus(boolean status) {
        this.status = status;
        this.put("status",status);
        return this;
    }
}
