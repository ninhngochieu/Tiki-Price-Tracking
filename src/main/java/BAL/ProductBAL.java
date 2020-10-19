package BAL;

import DAL.DAL;
import DAL.ProductDAL;
import DTO.ProductDTO;

import java.util.ArrayList;

public class ProductBAL {
    DAL productDAL = new ProductDAL();
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
}
