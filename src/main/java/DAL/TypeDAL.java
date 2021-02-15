package DAL;

import DTO.TypeDTO;

import java.sql.*;
import java.util.ArrayList;

public class TypeDAL extends DB implements DAL{

    public ArrayList<TypeDTO> getAllType() {
        ArrayList<TypeDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `types`";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                TypeDTO type = new TypeDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"));
                list.add(type);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public boolean searchType(TypeDTO x) {
        try {
            String sql = "SELECT 1 FROM `types` WHERE id = "+x.getId();
            Statement statement = this.connection.createStatement();
            return statement.executeQuery(sql).first();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean insert(Object o) {
        TypeDTO x = (TypeDTO) o;
        try {
            String sql = "INSERT INTO `types`(`id`, `name`, `image`) VALUES (?,?,?)";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1,x.getId());
            statement.setString(2,x.getName());
            statement.setString(3,x.getImage()==null?"":"");
            return statement.executeUpdate()!=0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Khong the insert vao DB!");
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
        TypeDTO x = (TypeDTO) o;
        try {
            String sql = "SELECT 1 FROM `types` WHERE id = "+x.getId();
            Statement statement = this.connection.createStatement();
            return statement.executeQuery(sql).first();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `types`";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                TypeDTO type = new TypeDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"));
                list.add(type);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

}
