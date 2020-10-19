package DAL;

import DTO.CommentDTO;
import DTO.ProductDTO;
import DTO.TypeDTO;
import DTO.Type_ProductDTO;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class TikiDAL {
    public ArrayList<TypeDTO> getTypeFromTiki() {
        ArrayList<TypeDTO> list = new ArrayList<>();
        try {
            Document tiki = Jsoup.connect("https://tiki.vn").get();
            Elements info = tiki.getElementsByAttributeValue("data-view-id","main_navigation_item").select("a");
            for (Element x:info) {
                //System.out.println(x.children().next().text());
                //System.out.println(x.absUrl("href").split(".")[2]);
                String id = x.absUrl("href").split("\\.")[2];
                String name = x.children().next().text();
                TypeDTO item = new TypeDTO(id,name,null);
                list.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Không thể lấy được dữ liệu!");
        }
        return list;
    }

    public int getTotalCount(TypeDTO x) {
        try {
            //int count = (int) getJSONObjectFromURL("https://tiki.vn/api/v2/categories/"+x.getId()+"?include=ancestors").get("product_count");//Lấy số sản phẩm của ngành hàng điện thoại máy tính bảng
            JSONObject obj = getJSONObjectFromURL("https://tiki.vn/api/v2/categories/"+x.getId()+"?include=ancestors");
            if (obj==null){
                System.out.println("Khong lay duoc du lieu");
                System.out.println("************************************");
            }else {
                return (int) obj.get("product_count");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Khong the doc so luong nganh hang!");
            System.out.println("************************************");
        }
        return 0;
    }
    private JSONObject getJSONObjectFromURL(String url) {
        try {
            return new JSONObject(IOUtils.toString(new URL(url), Charset.forName("UTF-8")));
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Khong the lay JSONObj");
            System.out.println("************************************");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Khong the lay du lieu!");
            System.out.println("************************************");
        }
        return null;
    }

    public ArrayList<Type_ProductDTO> getListIdOnPage(TypeDTO x, int page, ArrayList<Type_ProductDTO> list_product_id) {
        try {
            Document document = Jsoup.connect("https://tiki.vn/dien-thoai-may-tinh-bang/c"+x.getId()+"&page="+page).get();//Doc danh sach san pham tai danh muc type và trang page
            Elements elements = document.getElementsByAttribute("data-seller-product-id");//Lay danh sach cac elêmt
                for (Element el:elements) {
                    list_product_id.add(new Type_ProductDTO(el.attr("data-id"),x.getId()));
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list_product_id;
    }

    public ProductDTO getProductFromTiki(Type_ProductDTO id_item) {
        JSONObject productObj = getJSONObjectFromURL("https://tiki.vn/api/v2/products/"+id_item.getId());
        //System.out.println(id);
        //System.out.println(productJSON);
        if (productObj==null){
            return null;
        }else {
            try {
                return new ProductDTO(
                        Integer.toString(productObj.getInt("id")),
                        productObj.getString("name"),
                        productObj.getString("thumbnail_url"),
                        id_item.getId_items(),
                        productObj.getInt("price"));
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("Khong ton tai thuoc tinh nay trong JSONObj!");
                return null;
            }
        }
    }

    public int getPriceOfProduct(ProductDTO p) {
        try {
            JSONObject obj = getJSONObjectFromURL("https://tiki.vn/api/v2/products/"+p.getId());
            if(obj!=null&&obj.has("price")){
                return obj.getInt("price");
            }else {
                return 0;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Khong the lay gia san pham!");
        }
        return 0;
    }

    public ArrayList<CommentDTO> getCommentsAllPage(ProductDTO p) {
        ArrayList<CommentDTO> comments = new ArrayList<>();
        JSONObject obj = getJSONObjectFromURL("https://tiki.vn/api/v2/reviews?product_id="+p.getId()+"&sort=score&limit=15");
        try {
            if(obj.getInt("reviews_count")==0){//Neu ko co comment nao
                System.out.println("Khong co comment nao duoc lay");
            }else {
                JSONArray commentArray = obj.getJSONArray("data");//Lay tat ca comment
                commentArray.forEach(item->{
                    JSONObject c = (JSONObject)item;
                    CommentDTO commentDTO = new CommentDTO(
                            c.getInt("id")+"",
                            c.getString("title"),
                            c.getString("content"),
                            c.getInt("thank_count"),
                            c.getFloat("rating"),
                            c.getInt("product_id")+""
                    );
                comments.add(commentDTO);
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Khong ton tai thuoc tinh nay!");
        }
        System.out.println(comments);
        return comments;
    }

}
