package Server;

import BAL.BAL;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server17 implements Runnable{
    private Socket socket;
    BufferedWriter out;
    BufferedReader in;
    private ArrayList<Object> list;

    BAL bal = new BAL();
    public Server17(Socket socket) {
        this.socket = socket;
        this.list = bal.list_product;
        try {
           this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> list = (List<String>) objectInputStream.readObject();
            System.out.println(list);
        }catch (Exception e){

        }
        closeConnection();
    }

    private void sendDataToClient(String data) {
        try {
            out.write("");
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
