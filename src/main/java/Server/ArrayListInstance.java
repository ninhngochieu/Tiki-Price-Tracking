package Server;

import BAL.ProductBAL;
import BAL.TypeBAL;

import java.util.ArrayList;

public class ArrayListInstance {
    public static ArrayList list_type;
    public static ArrayList list_product;
    private static ArrayListInstance listInstance=null;
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
