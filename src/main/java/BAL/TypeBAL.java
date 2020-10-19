package BAL;

import DAL.DAL;
import DAL.TypeDAL;
import DTO.TypeDTO;

import java.util.ArrayList;

public class TypeBAL {
    DAL typeDAL = new TypeDAL();

    public ArrayList<Object> getAllType() {
        return typeDAL.getAll();
    }

    public boolean found(TypeDTO x) {
        return typeDAL.found(x);
    }

    public void insertNewType(TypeDTO x) {
        if(typeDAL.insert(x)){
            System.out.println(x+" được thêm thành công!");
        }else {
            System.out.println("Thêm thất bại. Có lỗi xảy ra!");
        }
    }
}
