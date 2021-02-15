package DAL;

import java.util.ArrayList;

public interface DAL {
    boolean insert(Object o);
    boolean update(Object o);
    boolean delete(Object o);
    boolean found(Object o);
    ArrayList<Object> getAll();
}
