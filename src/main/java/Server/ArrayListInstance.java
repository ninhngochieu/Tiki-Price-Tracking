package Server;

import BAL.ProductBAL;
import BAL.TypeBAL;
import DTO.ProductDTO;

import java.util.ArrayList;

public class ArrayListInstance {
    public static ArrayList<Object> list_type;
    public static ArrayList<Object> list_product;
    private static ArrayListInstance listInstance;
    private ArrayListInstance(){
        ProductBAL productBAL = new ProductBAL();
        TypeBAL typeBAL = new TypeBAL();
        list_type = typeBAL.getAllType();
        list_product =productBAL.getAllProduct();
    }
    public static ArrayListInstance getInstance(){
        if (listInstance==null){
            listInstance = new ArrayListInstance();
        }
        return listInstance;
    }
}
