package Server;

import BAL.BAL;

import java.util.ArrayList;

public class ArrayListInstance {
    public ArrayList list_type;
    public ArrayList list_product;
    private static ArrayListInstance listInstance;
    private ArrayListInstance(){
        BAL bal = new BAL();
        this.list_type = bal.list_type;
        this.list_product = bal.list_product;
    }
    public static ArrayListInstance getInstance(){
        if (listInstance==null){
            listInstance = new ArrayListInstance();
        }
        return listInstance;
    }
}
