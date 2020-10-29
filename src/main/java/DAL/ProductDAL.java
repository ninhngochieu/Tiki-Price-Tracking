package DAL;

import DTO.ProductDTO;
import DTO.TypeDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDAL extends DB implements DAL {

    public int getPriceById(String id) {
        try {
            String sql = "SELECT * FROM `history` WHERE id_product = "+id+" ORDER BY id DESC LIMIT 1";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            return rs.getInt("current_price");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean insert(Object o) {
        ProductDTO productTiki = (ProductDTO) o;
        try {
            String sql = "INSERT INTO `product`(`id`, `name`, `image`, `price`, `id_item`) VALUES (?,?,?,?,?)";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1,productTiki.getId());
            statement.setString(2,productTiki.getName());
            statement.setString(3,productTiki.getImage());
            statement.setInt(4,productTiki.getPrice());
            statement.setString(5,productTiki.getId_item());
            return statement.executeUpdate()!=0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Object o) {
        ProductDTO x = (ProductDTO) o;
        try {
            String sql = "UPDATE `product` SET " +
                    "`name`=?," +
                    "`image`=?," +
                    "`price`=?," +
                    "`id_item`=? " +
                    "WHERE `id` = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1,x.getName());
            statement.setString(2,x.getImage());
            statement.setInt(3,x.getPrice());
            statement.setString(4,x.getId_item());
            statement.setString(5,x.getId());
//            statement.setInt(1,x.getPrice());
//            statement.setString(2,x.getId());
            return statement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public boolean found(Object o) {
        ProductDTO p = (ProductDTO) o;
        try {
            String sql = "SELECT 1 FROM `product` WHERE id = "+p.getId();
            Statement statement = this.connection.createStatement();
            return statement.executeQuery(sql).first();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `product`";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ProductDTO product = new ProductDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("id_item"),
                        rs.getInt("price")
                );
                productDTOS.add(product);
            }
            return productDTOS;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<Object> getByName(String name, int per_page, int current_page) {
        ArrayList<Object> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `product` WHERE `name` LIKE '%"+name+"%' LIMIT "+per_page+" OFFSET "+(current_page-1)*per_page+"";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ProductDTO product = new ProductDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("id_item"),
                        rs.getInt("price")
                );
                productDTOS.add(product);
            }
            System.out.println(productDTOS);
            return productDTOS;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<Object> getByIdName(String id_item, String name, int per_page, int current_page) {
        ArrayList<Object> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `product` WHERE `id_item` = '"+id_item+"' AND `name` LIKE '%"+name+"%' LIMIT "+per_page+" OFFSET "+(current_page-1)*per_page+"";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ProductDTO product = new ProductDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("id_item"),
                        rs.getInt("price")
                );
                productDTOS.add(product);
            }
            return productDTOS;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<Object> getTotalByName(String name) {
        ArrayList<Object> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `product` WHERE `name` LIKE '%"+name+"%'";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ProductDTO product = new ProductDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("id_item"),
                        rs.getInt("price")
                );
                productDTOS.add(product);
            }
            System.out.println(productDTOS);
            return productDTOS;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
