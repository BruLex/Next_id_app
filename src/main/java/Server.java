import java.net.*;
import java.io.*;
import java.sql.SQLException;

public class Server {

    public static void main(String[] ar) {
        int port = 9999;

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            try {
                DB.getInst().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Connection to DB closed, quiting server");
        }));

        try (ServerSocket ss = new ServerSocket(port)){

            while(true) {
                System.out.println("Waiting for a client...");
                new ServerThr(ss.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("error " + port);
            System.exit(-1);
        }
    }
}