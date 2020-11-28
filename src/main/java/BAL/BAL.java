package BAL;
import DTO.CommentDTO;
import DTO.HistoryDTO;
import DTO.ProductDTO;
import DTO.TypeDTO;
import Server.ArrayListInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;

public class BAL {
    TypeBAL typeBAL = new TypeBAL();
    ProductBAL productBAL = new ProductBAL();
    HistoryBAL historyBAL = new HistoryBAL();
    TikiBAL tikiBAL = new TikiBAL();
    CommentBAL commentBAL = new CommentBAL();
    DetailBAL detailBAL = new DetailBAL();
    ArrayList<Object> list_type;
    ArrayList<Object> list_product;
    public BAL() {
        ArrayListInstance listInstance = ArrayListInstance.getInstance();
        this.list_type = listInstance.list_type;
        this.list_product = listInstance.list_product;
    }

    public void insertHistory() {
        int i=1;
        for (Object x:list_product) {
            System.out.println("****************************************************");
            tikiBAL.update((ProductDTO) x);
            if(productBAL.update((ProductDTO) x)&&historyBAL.insertHistory((ProductDTO) x)){
                System.out.println(i+". Them lich su "+x);
            }else {
                System.out.println("Thêm thất bại. Có lỗi xảy ra!");
            }
            i++;
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

    public Object getDetailById(HashMap<String, String> params, HashMap<String, Object> data1) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        try {
            ArrayList<Object> getHistoryAsync = executor.submit(new Callable<ArrayList<Object>>() {
                @Override
                public ArrayList<Object> call() throws Exception {
                    System.out.println("Get history");
                    return historyBAL.getHistoryById(params.get("id"));
                }
            }).get();
            ArrayList<Object> getCommentAsync = executor.submit(new Callable<ArrayList<Object>>() {
                @Override
                public ArrayList<Object> call() throws Exception {
                    System.out.println("Get comment");
                    return historyBAL.getHistoryById(params.get("id"));
                }
            }).get();
            Object maxPrice = executor.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    System.out.println("Get max");
                    return historyBAL.maxPrice(params.get("id"));
                }
            });
            Object minPrice = executor.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    System.out.println("Get min");
                    return historyBAL.maxPrice(params.get("id"));
                }
            });

            data1.put("max_price",maxPrice);
            data1.put("min_price",minPrice);
            executor.shutdown();
            while (!executor.isTerminated()){}
            return detailBAL.getDetailProduct(params.get("id"),getHistoryAsync,getCommentAsync);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        ArrayList<Object> historyDTOS = historyBAL.getHistoryById(params.get("id"));
//        ArrayList<Object> commentDTOS = commentBAL.getCommentById(params.get("id"));
//        data1.put("max_price",historyBAL.maxPrice(params.get("id")));
//        data1.put("min_price",historyBAL.minPrice(params.get("id")));
//        return detailBAL.getDetailProduct(params.get("id"),historyDTOS,commentDTOS);
        return new Object();
    }
}
