package DAL;

import DTO.CommentDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDAL extends DB implements DAL{

    @Override
    public boolean insert(Object o) {
        CommentDTO c = (CommentDTO) o;
        try {
            String sql = "INSERT INTO `comment`(`id`, `title`, `content`, `thank_count`, `rating`, `id_product`) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1,c.getId());
            statement.setString(2,c.getTitle());
            statement.setString(3,c.getContent());
            statement.setInt(4,c.getThank_count());
            statement.setFloat(5,c.getRating());
            statement.setString(6,c.getId_product());
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

}
