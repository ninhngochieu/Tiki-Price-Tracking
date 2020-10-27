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
    BufferedWriter out;
    BufferedReader in;
    private ArrayList<Object> listSP;
    private ArrayList<Object> listType;
    private ArrayListInstance listInstance;
    private ProductBAL productBAL;
    private HistoryBAL historyBAL;
    private BAL BAL;

    public Server17(Socket socket) {
        this.socket = socket;
        listInstance = ArrayListInstance.getInstance();
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
                String json = mapper.writeValueAsString(result);
                //obj.put("data",json).put("key","123456").put("status",true);

                HashMap<String,Object> map = new HashMap();
                map.put("data",result);
                map.put("key","123456");
                map.put("status",true);
                JSONObject obj = new JSONObject(map);

                System.out.println(params+obj.toString());
                sendDataToClient(obj.toString());

            }
        }catch (Exception e){

        }
        closeConnection();
    }

    private Object processingData(HashMap<String, String> params) {
        switch (params.get("action")){
            case "getAllCategory":
                return this.listType;
            case "search":
                return productBAL.getAllProductWithParam(params);
            case "detailProduct":
                return BAL.getDetailById(params);
            default:break;
        }
        return new Object();
    }

    private HashMap<String, String> processingParameter(String[] p) {
        HashMap<String,String> params = new HashMap<>();
        for (int i = 0; i < p.length; i++) {
            String action[] = p[i].split("=");
            params.put(action[0],action[1]);
        }
        return params;
    }

    private void sendDataToClient(String data) {
        try {
            out.write(data);
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
