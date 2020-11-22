package BAL;

import DAL.ProductDAL;
import DTO.ProductDTO;
import Server.ArrayListInstance;
import Server.PaginateList;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ProductBAL{
    private static ArrayListInstance instance = ArrayListInstance.getInstance();
    private ExecutorService service = Executors.newFixedThreadPool(2);
    private ProductDAL productDAL = new ProductDAL();

    public ArrayList<Object> getAllProduct() {
        return productDAL.getAll();
    }

    public boolean update(ProductDTO x) {
        return productDAL.update(x);
    }

    public boolean found(ProductDTO p) {
        return productDAL.found(p);
    }


    public void insertNewProduct(ProductDTO p) {
        if(productDAL.insert(p)){
            System.out.println("Them san pham: "+p);
        }else {
            System.out.println("Them that bai. Co loi xay ra");
        }
    }

    public PaginateList getAllProductWithParam(HashMap<String, String> params, int per_page, int current_page) {
        String idCategory = params.get("idCategory");
        String key = params.get("key");

        if(!instance.result_list.containsKey(key)) instance.result_list.put(key,searchAll(key,productDAL.getByName(key)));
        if(instance.result_list.get(key).isEmpty()) instance.result_list.put(key,productDAL.getByAllName(key));

        if(idCategory.equalsIgnoreCase("1")){
            ArrayList<ProductDTO> fillter_price= new ArrayList(instance.result_list.get(key));
            fillterPrice(fillter_price,params);
//            return new PaginateList(instance.result_list.get(key),per_page,current_page).getResult();//tra ve ket qua phan trang
            return new PaginateList(fillter_price,per_page,current_page).getResult();//tra ve ket qua phan trang
        }else {
            ArrayList temp_list = new ArrayList(instance.result_list.get(key).stream().filter(x->{
                return x.getId_item().equalsIgnoreCase(idCategory);
            }).collect(Collectors.toList())); //List tam da loc theo id theo ket qua tren

            ArrayList<ProductDTO> fillter_price= new ArrayList(temp_list);
            fillterPrice(fillter_price,params);
            return new PaginateList(temp_list,per_page,current_page).getResult();
        }
    }

    private void fillterPrice(ArrayList<ProductDTO> fillter_price, HashMap<String, String> params) {
        if(params.get("min_price")!=null){
            fillter_price.removeIf(x->{
                return x.getPrice() < Integer.parseInt(params.get("min_price"));
            });
        }
        if(params.get("max_price")!=null){
            fillter_price.removeIf(x->{
                return x.getPrice() > Integer.parseInt(params.get("max_price"));
            });
        }
    }
    private ArrayList<ProductDTO> searchAll(String key, ArrayList<ProductDTO> list_product) {
            ArrayList<ProductDTO> fillter = new ArrayList(list_product
                    .stream().filter(x->{
                        ProductDTO p = (ProductDTO) x;
                        p.setIndex(99);
                        if(chuanHoa(p.getName()).toLowerCase().contains(chuanHoa(key))){
                            String names[] = p.getName().toLowerCase().split(" ");
                            boolean flag = true;
                            for (int i = 0; (i < names.length && flag) == true; i++) {
                                if(chuanHoa(names[i]).toLowerCase().equalsIgnoreCase(chuanHoa(key))) {
                                    p.setIndex(i);
                                    flag = false;
                                }
                            }
                            return true;
                        }
                        return false;
                    })
                    .collect(Collectors.toList()));
            Collections.sort(fillter);
            return fillter;
     }
    private String chuanHoa(String str) {
        str = str.replaceAll("à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ", "a");
        str = str.replaceAll("è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ", "e");
        str = str.replaceAll("ì|í|ị|ỉ|ĩ", "i");
        str = str.replaceAll("ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ", "o");
        str = str.replaceAll("ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ", "u");
        str = str.replaceAll("ỳ|ý|ỵ|ỷ|ỹ", "y");
        str = str.replaceAll("đ", "d");

        str = str.replaceAll("À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ", "A");
        str = str.replaceAll("È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ", "E");
        str = str.replaceAll("Ì|Í|Ị|Ỉ|Ĩ", "I");
        str = str.replaceAll("Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ", "O");
        str = str.replaceAll("Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ", "U");
        str = str.replaceAll("Ỳ|Ý|Ỵ|Ỷ|Ỹ", "Y");
        str = str.replaceAll("Đ", "D");
        return str;
    }

    public Object suggestNameProduct(HashMap<String, String> params) {
        String key = params.get("key");
        ArrayList list = productDAL.suggestProductByName(key);
        if(list.isEmpty()) list = productDAL.suggestAllProductByName(key);
        return list;
    }

    public ArrayList<String> getAllNameProduct() {
        return this.productDAL.getAllNameProduct();
    }
}
