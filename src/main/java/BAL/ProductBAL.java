package BAL;

import DAL.DAL;
import DAL.ProductDAL;
import DTO.ProductDTO;
import Server.ArrayListInstance;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductBAL{
    private static ArrayListInstance instance = ArrayListInstance.getInstance();
    private ProductDAL productDAL = new ProductDAL();

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

    public Object suggestNameProduct(HashMap<String, String> params) {
        ArrayList<ProductDTO> fillter_list = instance.list_product;
        fillter_list.stream().filter(x->x.getId_item().equals(params.get("idCategory")));
        System.out.println(fillter_list);
        return null;
    }

//    public Object getSuggestNameProduct(HashMap<String, String> params) {
//        ArrayList<ProductDTO> list_product= instance.list_product;
//        ArrayList<String> name_products=new ArrayList<>();
//        list_product.forEach(x->{
//            if(!params.get("idCategory").equalsIgnoreCase("1")&&x.getName().contains(params.get("key"))) {
//                name_products.add(x.getName());
//            }else {
//                if(x.getName().contains(params.get("key"))){
//                    name_products.add(x.getName());
//                }
//            }
//        });
//        return name_products;
//    }
}
