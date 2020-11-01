package Server;

import java.util.ArrayList;
import java.util.Collection;

public class PaginateList<E> extends ArrayList<E>{
    private int total;
    private int last_page;
    private int per_page;
    private int current_page;

    public PaginateList() {

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

    public PaginateList(Collection<? extends E> c) {
        super(c);
    }

    public int getPer_page() {
        return per_page;
    }

    public PaginateList perPage(int per_page) {
        this.per_page = per_page;
        this.last_page = this.total/per_page+1;
        return this;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public PaginateList currentPage(int current_page) {
        this.current_page = current_page;
        return this;
    }
    public ArrayList<E> getResult(){
        ArrayList<E> result = new ArrayList();
        int offset = (this.current_page-1)*this.per_page;
        for (int i = offset,flag = 0; i < this.size()&&flag<12;i++,flag++) {
            result.add(this.get(i));
        }
        return result;
    }
}
