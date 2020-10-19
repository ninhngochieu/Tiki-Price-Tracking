package BAL;

import DAL.DAL;
import DAL.HistoryDAL;
import DTO.ProductDTO;

public class HistoryBAL {
    DAL historyDAL = new HistoryDAL();
    public boolean insertHistory(ProductDTO x) {
        return historyDAL.insert(x);
    }
}
