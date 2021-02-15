package Server;

import BAL.ProductBAL;
import BAL.TypeBAL;
import DTO.ProductDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class ArrayListInstance {
    public static ArrayList<Object> list_type;
    public static ArrayList<Object> list_product;
    private static ArrayListInstance listInstance;
    public static HashMap<String,ArrayList<ProductDTO>> result_list;
    private ArrayListInstance(){
        ProductBAL productBAL = new ProductBAL();
        TypeBAL typeBAL = new TypeBAL();
        list_type = typeBAL.getAllType();
        list_product =productBAL.getAllProduct();
        result_list = new HashMap<>();
    }
    public static ArrayListInstance getInstance(){
        if (listInstance==null){
            listInstance = new ArrayListInstance();
        }
        return listInstance;
    }
}
