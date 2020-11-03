package BAL;

import DAL.HistoryDAL;
import DTO.ProductDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoryBAL {
    HistoryDAL historyDAL = new HistoryDAL();
    public boolean insertHistory(ProductDTO x) {
        return historyDAL.insert(x);
    }

    public ArrayList<Object> getHistoryById(String id_product) {
        return historyDAL.getHistoryById(id_product);
    }

    public Object getHistoryByDate(HashMap<String, String> params) {
        return new Object();
    }
}
