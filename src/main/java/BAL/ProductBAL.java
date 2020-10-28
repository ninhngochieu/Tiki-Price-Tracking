package BAL;

import DAL.DAL;
import DAL.ProductDAL;
import DTO.ProductDTO;
import Server.ArrayListInstance;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductBAL {
    ArrayListInstance instance = ArrayListInstance.getInstance();
    ProductDAL productDAL = new ProductDAL();

    public ArrayList<Object> getAllProduct() {
        return productDAL.getAll();
    }

    public boolean update(ProductDTO x) {
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

    public ArrayList<Object> getAllProductWithParam(HashMap<String, String> params) {
        if(params.get("idCategory").equalsIgnoreCase("1")){
            return productDAL.getByName(params.get("key"));
        }else {
            return productDAL.getByIdName(params.get("idCategory"),params.get("key"));
        }
    }

    public Object getSuggestNameProduct(HashMap<String, String> params) {
        ArrayList<ProductDTO> list_product= instance.list_product;
        ArrayList<String> name_products=new ArrayList<>();
//        if(params.get("idCategory").equalsIgnoreCase("1")) {
//            list_product.forEach(x->{
//                if(x.getName().contains(params.get("key"))){
//                    name_products.add(x.getName());
//                }
//            });
//        }
//
        list_product.forEach(x->{
            if(!params.get("idCategory").equalsIgnoreCase("1")&&x.getName().contains(params.get("key"))) {
                name_products.add(x.getName());
            }else {
                if(x.getName().contains(params.get("key"))){
                    name_products.add(x.getName());
                }
            }
        });
        return name_products;
    }
}
