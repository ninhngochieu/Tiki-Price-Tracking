package BAL;

import DAL.CommentDAL;
import DAL.DAL;
import DTO.CommentDTO;

import java.util.ArrayList;

public class CommentBAL {
    CommentDAL commentDAL = new CommentDAL();
    public void insertNewComment(CommentDTO c) {
        if(commentDAL.insert(c)){
            System.out.println(c.getId_product()+" da them comment");
        }else {
            System.out.println("Them comment that bai. Co loi xay ra!");
        }
    }

    public ArrayList<Object> getCommentById(String id_product) {
        return commentDAL.getCommentById(id_product);
    }
}
