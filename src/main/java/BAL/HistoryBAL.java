package BAL;

import DAL.HistoryDAL;
import DTO.HistoryDTO;
import DTO.ProductDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class HistoryBAL {
    HistoryDAL historyDAL = new HistoryDAL();
    public boolean insertHistory(ProductDTO x) {
        return historyDAL.insert(x);
    }

    public ArrayList<Object> getHistoryById(String id_product) {
        return historyDAL.getHistoryById(id_product);
    }

    public void getHistoryByDate(HashMap<String, String> params, HashMap<String, Object> data) {
/*        String date_start[] =  params.get("start").split("-");
        String date_end[] = params.get("end").split("-");*/
        String date_start =  params.get("start");
        String date_end =  params.get("end");
/*        String start = formatDate(date_start);//Xu ly ngay thang theo chuan cua SQL
        String end = formatDate(date_end);*/

        data.put("history",historyDAL.getALlHistoryByDate(params.get("id"),date_start,date_end));
        data.put("min_price",historyDAL.getMinHistoryByDate(params.get("id"),date_start,date_end));
        data.put("max_price",historyDAL.getMaxHistoryByDate(params.get("id"),date_start,date_end));
        System.out.println(data.toString());
    }

    private String formatDate(String[] date) {
        return date[2]+"-"+date[1]+"-"+date[0];
    }

    public Object maxPrice(String id) {
        return historyDAL.maxPrice(id);
    }

    public Object minPrice(String id) {
        return historyDAL.minPrice(id);
    }
}
