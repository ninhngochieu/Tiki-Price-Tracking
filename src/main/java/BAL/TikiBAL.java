package BAL;

import DAL.HistoryDAL;
import DAL.ProductDAL;
import DAL.TikiDAL;
import DAL.TypeDAL;
import DTO.CommentDTO;
import DTO.ProductDTO;
import DTO.TypeDTO;
import DTO.Type_ProductDTO;

import java.util.ArrayList;

public class TikiBAL {
    private TikiDAL tikiDAL = new TikiDAL();

    public int getCurrentPrice(ProductDTO p) {
        return tikiDAL.getPriceOfProduct(p);
    }

    public ArrayList<TypeDTO> getAllTypeTiki() {
        return tikiDAL.getTypeFromTiki();
    }

    public ArrayList<ProductDTO> getAllProductTiki(ArrayList<Object> list_type){
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        ArrayList<Type_ProductDTO> all_list_id = getListId(list_type);//Lấy danh sách tất cả các id dựa vào mã danh mục
        System.out.println(all_list_id);
        int i=1;
        for (Type_ProductDTO id_item:all_list_id) {//Duyệt từng sản phẩm và mã mặt hàng
            ProductDTO product = tikiDAL.getProductFromTiki(id_item);
            if (product != null) {
                productDTOS.add(product);
                System.out.println(i+" - "+product);
                i++;
            }else {
                System.out.println("Khong the lay san pham nay");
            }
        }
        return productDTOS;
    }

    private ArrayList<Type_ProductDTO> getListId(ArrayList<Object> list_type) {
        ArrayList<Type_ProductDTO> list_product_id = new ArrayList<>();
        for (Object t:list_type) {
            int product_count = tikiDAL.getTotalCount((TypeDTO) t);//Lấy count toàn bộ sản phẩm của danh mục này
            if(product_count==0) {
                System.out.println("Khong co so luong san pham");
            }else {
                product_count = product_count > 450 ? 450 : product_count;//Gioi han so san pham
                int offset = product_count/52+1;//Lay tong so trang cua san pham
                for (int i=1;i<=offset;i++){//Duyệt theo từng trang
                    tikiDAL.getListIdOnPage((TypeDTO) t,i,list_product_id);
                }
                System.out.println("Waiting...Current id "+list_product_id.size());
            }
        }
        return list_product_id;
    }

    public ArrayList<CommentDTO> getAllCommentTiki(ArrayList<Object> list_product) {
        ArrayList<CommentDTO> list = new ArrayList<>();
        int i=1;
        for (Object o:list_product) {
            ProductDTO p= (ProductDTO) o;
            ArrayList<CommentDTO>  commentOnPage = tikiDAL.getCommentsAllPage(p);
            list.addAll(commentOnPage);
            System.out.println(i+". Da lay xong comment san pham "+p.getId()+". So comment: "+list.size());
            i++;
        }
        return list;
    }
    public void setPrice_Review(ProductDTO x) {
        System.out.println("Price "+x.getPrice()+"/"+"Review count "+x.getReview_count());
        tikiDAL.setPriceAndReview(x);
        System.out.println("New price "+x.getPrice()+"/"+"New review count "+x.getReview_count());
    }

}
                                                                                                                                