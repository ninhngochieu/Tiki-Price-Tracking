package Server;

import BAL.BAL;
import BAL.ProductBAL;
import BAL.HistoryBAL;
import DTO.SenderDTO;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;

public class Server17 implements Runnable{
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    private ArrayList<Object> listSP;
    private ArrayList<Object> listType;
    private ProductBAL productBAL;
    private HistoryBAL historyBAL;
    private BAL BAL;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private static ArrayListInstance listInstance=ArrayListInstance.getInstance();

    public Server17(Socket socket) {
        this.socket = socket;
        this.productBAL = new ProductBAL();
        this.BAL = new BAL();
        this.historyBAL = new HistoryBAL();
        this.listSP = listInstance.list_product;
        this.listType = listInstance.list_type;
        try {
           this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            String data="";
            while (true){
                data = this.in.readLine();
                HashMap<String,String> params = processingParameter(data.split("&"));//String parameter: action=search&name="San pham 1"
                HashMap<String,Object> map = processingData(params);
                System.out.println(params+map.toString());
                sendDataToClient(map);

            }
        }catch (Exception e){

        }
        closeConnection();
    }

    private HashMap processingData(HashMap<String, String> params) {
        HashMap<String,Object> map = new HashMap<>();
        switch (params.get("action")){
            case "getAllCategory":
                map = new SenderDTO(this.listType,"123456",true);
                return map;
            case "search":
                //int total = productBAL.getTotal(params);
                int current_page = Integer.parseInt(params.get("page"));
                int per_page = 16;
                PaginateList result_list = productBAL.getAllProductWithParam(params,per_page,current_page);
                map = new SenderDTO(result_list,
                        true,result_list.getTotal(),
                        per_page,current_page,
                        result_list.getLast_page());
                return map;
            case "detailProduct":
                map = new SenderDTO(BAL.getDetailById(params),true);
                return map;
            case "suggest_result":
                map = new SenderDTO(productBAL.suggestNameProduct(params),true);
                return map;
            case "fillter_hisory":
                HashMap<String,Object> data = new HashMap();
                historyBAL.getHistoryByDate(params,data);//Can lay nhieu du lieu nhu min va max
                map = new SenderDTO(data.get("history"),
                        true,
                        Integer.parseInt(data.get("min_price").toString()),
                        Integer.parseInt(data.get("max_price").toString()));
                break;
            default:break;
        }
        return map;
    }

    private HashMap<String, String> processingParameter(String[] p) {
        HashMap<String,String> params = new HashMap<>();
        for (int i = 0; i < p.length; i++) { ;
            try{
                String action[] = p[i].split("=");
                params.put(action[0],action[1]);
            }catch (Exception e){

            }
        }
        return params;
    }

    private void sendDataToClient(HashMap<String, Object> map) {
        try {
            out.write(new JSONObject(map).toString());
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void closeConnection() {
        System.out.println(socket.toString()+"is closed!");
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
