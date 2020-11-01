package Server;

import DTO.ProductDTO;

import java.util.ArrayList;

public class PaginateList extends ArrayList{
    private int total;
    private int last_page;
    private int per_page;
    private int current_page;
    private ArrayList<ProductDTO> list_paginate;

    public PaginateList(ArrayList list_paginate, int per_page, int current_page) {
        this.per_page = per_page;
        this.current_page = current_page;
        this.list_paginate = list_paginate;
        this.total = list_paginate.size();
        this.last_page = this.total%16==0?this.total/per_page:this.total/per_page+1;
    }

    public PaginateList(int total, int last_page, int per_page, int current_page) {
        this.total = total;
        this.last_page = last_page;
        this.per_page = per_page;
        this.current_page = current_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public ArrayList getList_paginate() {
        return list_paginate;
    }

    public void setList_paginate(ArrayList list_paginate) {
        this.list_paginate = list_paginate;
    }

    public PaginateList getResult(){
        PaginateList result = new PaginateList(this.total,this.last_page,this.per_page,this.current_page);
        int offset = (current_page-1)*per_page;
        for (int i = offset,flag = 0; i < total&&flag<12;i++,flag++) {
            result.add(this.list_paginate.get(i));
        }
        return result;
    }
}

