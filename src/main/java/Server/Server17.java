package Server;

import BAL.BAL;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server17 implements Runnable{
    private Socket socket;
    BufferedWriter out;
    BufferedReader in;
    private ArrayList<Object> listSP;
    private ArrayList<Object> listCategory;

    BAL bal = new BAL();
    public Server17(Socket socket) {
        this.socket = socket;
        this.listSP = bal.list_product;
        this.listCategory = bal.list_type;

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
            while (true){
                data = this.in.readLine();
                String json = mapper.writeValueAsString(this.listCategory);
                System.out.println(json);
                sendDataToClient(json);
            }
        }catch (Exception e){

        }
        closeConnection();
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
        System.out.println(socket.toString()+"is closing connection!");
        try {
            in.close();
            //out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
