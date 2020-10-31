package BAL;

import DAL.ProductDAL;
import DTO.ProductDTO;
import Server.ArrayListInstance;

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

    public ArrayList getAllProductWithParam(HashMap<String, String> params, int per_page, int current_page,HashMap total_last_page) {
        String idCategory = params.get("idCategory");
        String key = params.get("key");

        //if(!instance.result_list.containsKey(key)) instance.result_list.put(key,searchAll(key));
        //if(!instance.result_list.containsKey(key)) instance.result_list.put(key,productDAL.getByName(key));
        if(!instance.result_list.containsKey(key)) instance.result_list.put(key,searchAll(key,productDAL.getByName(key)));
        //ArrayList<ProductDTO> result_list = searchAll(key);

        if(idCategory.equalsIgnoreCase("1")){
            //return searchAll(key,per_page,current_page);
            //return productDAL.getByName(params.get("key"),per_page,current_page);//SQL
            ArrayList<ProductDTO> list = new ArrayList<>();
            int total = instance.result_list.get(key).size();//Lay tong so trang
            int last_page = total/per_page + 1;//lay so trang cuoi cung

            total_last_page.put("total",total);
            total_last_page.put("last_page",last_page);
            int offset = (current_page-1)*per_page;//vi tri bat dau
            paginate(offset,instance.result_list.get(key),list);
            return list;//tra ve ket qua phan trang
        }else {
            //return productDAL.getByIdName(params.get("idCategory"),params.get("key"),per_page,current_page);
            ArrayList<ProductDTO> temp_list = new ArrayList(instance.result_list.get(key).stream().filter(x->{
                return x.getId_item().equalsIgnoreCase(idCategory);
            }).collect(Collectors.toList())); //List tam da loc theo id theo ket qua tren

            ArrayList<ProductDTO> list = new ArrayList<>();
            int total = temp_list.size();//Lay tong so trang
            int last_page = total/per_page + 1;//lay so trang cuoi cung


            total_last_page.put("total",total);
            total_last_page.put("last_page",last_page);

            int offset = (current_page-1)*per_page;//vi tri bat dau
            paginate(offset,temp_list,list);
            return list;//tra ve ket qua phan trang

        }
    }

    private void paginate(int offset, ArrayList<ProductDTO> result_list, ArrayList<ProductDTO> list) {
        int flag = 1;
        for (int i = offset; i < result_list.size()&&flag<=12;i++) {
            list.add(result_list.get(i));
            flag++;
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

    public int getTotal(HashMap<String, String> params) {
        return productDAL.getTotalByName(params.get("key")).size();
    }

    public Object suggestNameProduct(HashMap<String, String> params) {
        return new Object();
    }
}
