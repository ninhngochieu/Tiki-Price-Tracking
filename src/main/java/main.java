import BAL.BAL;
import BAL.TikiBAL;
import BAL.TypeBAL;
import DTO.ProductDTO;
import Server.Server17;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {
    private static ServerSocket server = null;
    public static void main(String[] args) {
        BAL balService = new BAL();
        ExecutorService service = Executors.newFixedThreadPool(2);

        //balService.insertNewType();
        //balService.insertNewProduct();
        //balService.insertHistory();
        //balService.insertComment();

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
