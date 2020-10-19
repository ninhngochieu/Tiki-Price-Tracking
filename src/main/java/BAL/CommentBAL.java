package BAL;

import DAL.CommentDAL;
import DAL.DAL;
import DTO.CommentDTO;

public class CommentBAL {
    DAL commentDAL = new CommentDAL();
    public void insertNewComment(CommentDTO c) {
        if(commentDAL.insert(c)){
            System.out.println(c.getId_product()+" da them comment");
        }else {
            System.out.println("Them comment that bai. Co loi xay ra!");
        }
    }
}
