package Server;

import BAL.*;
import DAL.ProductDAL;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jdk.jfr.Timespan;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

    private static ArrayListInstance listInstance=ArrayListInstance.getInstance();

    public Server17(Socket socket) {
        this.socket = socket;
        this.productBAL = new ProductBAL();
        this.historyBAL = new HistoryBAL();
        this.BAL = new BAL();
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
            ObjectMapper mapper = new ObjectMapper();
            while (true){ //
                data = this.in.readLine();
                HashMap<String,String> params = processingParameter(data.split("&"));//String parameter: action=search&name="San pham 1"
                Object result = processingData(params);

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
                map.put("data",this.listType);
                map.put("key","123456");
                map.put("status",true);
                return map;
            case "search":
                int total = productBAL.getTotal(params);
                int per_page = 12;
                int current_page = Integer.parseInt(params.get("page"));
                int last_page = total/12 +1;

                map.put("data",productBAL.getAllProductWithParam(params,per_page,current_page));
                map.put("key","123456");
                map.put("status",true);
                map.put("total",total);
                map.put("per_page",per_page);
                map.put("current_page",current_page);
                map.put("last_page",last_page);
                return map;
            case "detailProduct":
                map.put("data",BAL.getDetailById(params));
                map.put("key","123456");
                map.put("status",true);
                return map;
            default:break;
        }
        return map;
    }

    private HashMap<String, String> processingParameter(String[] p) {
        HashMap<String,String> params = new HashMap<>();
        for (int i = 0; i < p.length; i++) {
            String action[] = p[i].split("=");
            params.put(action[0],action[1]);
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
