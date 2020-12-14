package DAL;

import DTO.DetailDTO;
import DTO.ProductDTO;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DetailDAL extends DB {
    @Override
    public boolean insert(Object o) {
        return false;
    }

    @Override
    public boolean update(Object o) {
        return false;
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public boolean found(Object o) {
        return false;
    }

    @Override
    public ArrayList<Object> getAll() {
        return null;
    }

    public Object getDetailById(String id, ArrayList<Object> historyDTOS, ArrayList<Object> commentDTOS) {
        try {
            String sql = "SELECT * FROM `product` WHERE `id` = '"+id+"'";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ProductDTO product = null;
            if(rs.next()){
                product = new DetailDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("id_item"),
                        rs.getInt("price"),
                        rs.getInt("review_count"),
                        rs.getFloat("rating_average"),
                        new JSONObject(),
                        historyDTOS,
                        commentDTOS
                );
            }
            return product;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ProductDTO();
    }
}
