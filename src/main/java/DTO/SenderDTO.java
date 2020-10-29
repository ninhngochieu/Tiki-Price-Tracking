package DTO;

import java.util.ArrayList;
import java.util.HashMap;

public class SenderDTO extends HashMap<String,Object>{
    private Object data;
    private String key;
    private boolean status = true;

    public SenderDTO(Object data, String key) {
        this.data = data;
        this.key = key;

        this.put("data",data);
        this.put("key",key);
    }

    public SenderDTO(Object data, String key, boolean status) {
        this.data = data;
        this.key = key;
        this.status = status;

        this.put("data",data);
        this.put("key",key);
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
