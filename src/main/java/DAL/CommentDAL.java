package DAL;

import DTO.CommentDTO;
import DTO.ProductDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CommentDAL extends DB implements DAL{

    @Override
    public boolean insert(Object o) {
        CommentDTO c = (CommentDTO) o;
        try {
            String sql = "INSERT INTO `comment`(`id`, `title`, `content`, `thank_count`, `rating`, `id_product`, `full_name`, `purchased_at`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1,c.getId());
            statement.setString(2,c.getTitle());
            statement.setString(3,c.getContent());
            statement.setInt(4,c.getThank_count());
            statement.setFloat(5,c.getRating());
            statement.setString(6,c.getId_product());
            statement.setString(7,c.getFull_name());
            statement.setInt(8,c.getPurchased_at());
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

    public ArrayList<Object> getCommentById(String id_product) {
        ArrayList<Object> commentDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `comment` WHERE `id_product` = '"+id_product+"'";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                CommentDTO comment = new CommentDTO(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("thank_count"),
                        rs.getFloat("rating"),
                        rs.getString("id_product")
                );
                commentDTOS.add(comment);
            }
            return commentDTOS;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
