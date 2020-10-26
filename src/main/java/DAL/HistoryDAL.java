package DAL;

import DTO.HistoryDTO;
import DTO.ProductDTO;

import java.sql.*;
import java.util.ArrayList;

public class HistoryDAL extends DB implements DAL{

    @Override
    public boolean insert(Object o) {
        ProductDTO product = (ProductDTO) o;
        try {
            String sql = "INSERT INTO `history`(`id_product`, `last_update`, `current_price`) VALUES (?,?,?)";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, product.getId());
            statement.setTimestamp(2,new Timestamp(System.currentTimeMillis()));
            statement.setInt(3,product.getPrice());
            return statement.executeUpdate()!=0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    public ArrayList<Object> getHistoryById(String id) {
        ArrayList<Object> historyDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `history` WHERE `id_product` = '"+id+"'";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                HistoryDTO history = new HistoryDTO(
                        rs.getString("id"),
                        rs.getString("id_product"),
                        rs.getTimestamp("last_update"),
                        rs.getInt("current_price")
                );
                historyDTOS.add(history);
            }
            return historyDTOS;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
