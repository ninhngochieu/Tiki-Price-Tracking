package BAL;

import DAL.ProductDAL;
import DTO.ProductDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductBAL {
    ProductDAL productDAL = new ProductDAL();
    public ArrayList<Object> getAllProduct() {
        return productDAL.getAll();
    }

    public boolean updatePrice(ProductDTO x) {
        return productDAL.update(x);
    }

    public boolean found(ProductDTO p) {
        return productDAL.found(p);
    }

    public void insertNewProduct(ProductDTO p) {
        if(productDAL.insert(p)){
            System.out.println("Them san pham: "+p);
        }else {
            System.out.println("Them that bai. Co loi xay ra");
        }
    }

    public ArrayList<Object> getAllProductWithParam(HashMap<String, String> params, int per_page, int current_page) {
        if(params.get("idCategory").equalsIgnoreCase("1")){
            return productDAL.getByName(params.get("key"),per_page,current_page);
        }else {
            return productDAL.getByIdName(params.get("idCategory"),params.get("key"),per_page,current_page);
        }
    }

    public int getTotal(HashMap<String, String> params) {
        return productDAL.getTotalByName(params.get("key")).size();
    }
}
