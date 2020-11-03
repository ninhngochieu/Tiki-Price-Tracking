import BAL.BAL;
import Server.Server17;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {
    private static ServerSocket server = null;
    public static void main(String[] args) {
        BAL balService = new BAL();
        ExecutorService service = Executors.newFixedThreadPool(2);
//        switch (args[0]){
//            case "1":balService.insertNewType();
//                break;
//            case "2":balService.insertNewProduct();
//                break;
//            case "3":balService.insertHistory();
//                break;
//            case "4":balService.insertComment();
//                break;
//            case "5": startSever(5002,service);
//            default: tutorial();
//                break;
//        }
        //balService.insertNewType();
        //balService.insertNewProduct();
        balService.insertHistory();
        //balService.insertComment();
        //startSever(5002,service);
    }

    private static void tutorial() {
        System.out.println("1. Insert new type");
        System.out.println("2. Insert new product");
        System.out.println("3. Update history");
        System.out.println("4. Insert comment");
        System.out.println("5. Start Server");
    }

    private static void startSever(int port, ExecutorService service) {
        try {
            server = new ServerSocket(5002);
            System.out.println("Waiting for client....!");
            while (true){
                Socket socket = server.accept();
                System.out.println(socket.toString()+" connected!");
                service.execute(new Server17(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
