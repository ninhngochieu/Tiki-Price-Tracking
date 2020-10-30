package BAL;

import DAL.ProductDAL;
import DTO.ProductDTO;
import Server.ArrayListInstance;

import java.util.*;
import java.util.stream.Collectors;

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

    public Object getAllProductWithParam(HashMap<String, String> params, int per_page, int current_page) {
        String idCategory = params.get("idCategory");
        String key = params.get("key");
        if(idCategory.equalsIgnoreCase("1")){
            //return test(key);
            return productDAL.getByName(params.get("key"),per_page,current_page);
        }else {
            return productDAL.getByIdName(params.get("idCategory"),params.get("key"),per_page,current_page);
        }
    }

    private Object test(String key) {
        ArrayList<ProductDTO> fillter = new ArrayList(instance.list_product
                .stream().filter(x->{
                    ProductDTO p = (ProductDTO) x;
                    if(p.getName().toLowerCase().contains(key)){
                        String names[] = p.getName().toLowerCase().split(" ");
                        boolean flag = true;
                        for (int i = 0; (i < names.length && flag) == true; i++) {
                            if(names[i].toLowerCase().equalsIgnoreCase(key)) {
                                p.setIndex(i);
                                flag = false;
                            }
                        }
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList()));
        Collections.sort(fillter);
        fillter.forEach(x->{
            System.out.println(x.toString());
        });
        return fillter;
    }

    public int getTotal(HashMap<String, String> params) {
        return productDAL.getTotalByName(params.get("key")).size();
    }

    public Object suggestNameProduct(HashMap<String, String> params) {
        return new Object();
    }
}
