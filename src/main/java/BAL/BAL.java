package BAL;
import DTO.CommentDTO;
import DTO.ProductDTO;
import DTO.TypeDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class BAL {
    TypeBAL typeBAL = new TypeBAL();
    ProductBAL productBAL = new ProductBAL();
    HistoryBAL historyBAL = new HistoryBAL();
    TikiBAL tikiBAL = new TikiBAL();
    CommentBAL commentBAL = new CommentBAL();

    ArrayList<Object> list_type=null;
    public ArrayList<Object> list_product=null;
    ArrayList<CommentDTO> list_comment=null;

    public BAL() {
        this.list_type = typeBAL.getAllType();
        this.list_product = productBAL.getAllProduct();
    }

    public void insertHistory() {
        int i=1;
        for (Object x:list_product) {
            int current_price = tikiBAL.getCurrentPrice((ProductDTO) x);
            if(current_price==0){
                System.out.println("****************San pham khong ton tai!*****************");
            }else {
                ((ProductDTO) x).setPrice(current_price);
                if(productBAL.updatePrice((ProductDTO) x)&&historyBAL.insertHistory((ProductDTO) x)){
                    System.out.println(i+". Them lich su "+x);
                }else {
                    System.out.println("Thêm thất bại. Có lỗi xảy ra!");
                }
                i++;
            }
        }
    }

    public void insertNewProduct() {
        int i=1;
        ArrayList<ProductDTO> listTiki = tikiBAL.getAllProductTiki(this.list_type);
        for (ProductDTO p:listTiki) {
            if(productBAL.found(p)){
                System.out.println(p+" da ton tai!");
            }else {
                System.out.print(i+" - ");
                productBAL.insertNewProduct(p);
                i++;
            }
        }
    }

    public void insertNewType() {
        ArrayList<TypeDTO> typeDTOS = tikiBAL.getAllTypeTiki();
        for (TypeDTO x:typeDTOS) {
            if(typeBAL.found(x)){
                System.out.println(x+" đã tồn tại!");
            }else {
                typeBAL.insertNewType(x);
            }
        }
    }

    public void insertComment() {
        ArrayList<CommentDTO> commentDTOS = tikiBAL.getAllCommentTiki(list_product);
        int i=1;
        for (CommentDTO c:commentDTOS) {
            commentBAL.insertNewComment(c);
            System.out.println(i+" da them "+c+" vao DB");
            i++;
        }
    }
}
