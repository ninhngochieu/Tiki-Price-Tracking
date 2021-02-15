import BAL.BAL;
import Server.Server17;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {
    public static void main(String[] args) {
        BAL balService = new BAL();
        ExecutorService service = Executors.newFixedThreadPool(4);//Tạo 2 pool chứa các luồng
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
        //balService.insertNewType();//Thêm loai mới
        //balService.insertNewProduct();//Thêm sản phâm mới
        //balService.insertHistory();//Thêm lịch sử giá mới
        //balService.insertComment();//Thêm comment mới
        startSever(5003,service);//Chạy server
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
            ServerSocket server = new ServerSocket(port);//Tạo sv với port = 5003
            System.out.println("Waiting for client....!");
            while (true){
                Socket socket = server.accept();
                System.out.println(socket.toString()+" connected!");
                service.execute(new Server17(socket));//Nếu có cl kết nối thì tạo 1 luồng chạy sv
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
