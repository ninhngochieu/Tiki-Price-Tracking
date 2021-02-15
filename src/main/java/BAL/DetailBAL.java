package BAL;

import DAL.DetailDAL;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailBAL {
    DetailDAL detailDAL = new DetailDAL();

    public Object getDetailProduct(String id, ArrayList<Object> historyDTOS, ArrayList<Object> commentDTOS) {
        return detailDAL.getDetailById(id,historyDTOS,commentDTOS);
    }
}
