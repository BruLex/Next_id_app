import java.net.*;
import java.io.*;

public class Client {

    private static final String address = "127.0.0.1";
    private static final int serverPort = 9999;
    private static Socket socket;

    public static void main(String[] ar) {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("closed");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.

            InputStream sin = socket.getInputStream();
            OutputStream sut = socket.getOutputStream();

            PrintWriter out = new PrintWriter(sut,true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sin));

            // Создаем поток для чтения с клавиатуры.
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String line;
            Boolean active = true;
            while (active && !socket.isClosed()) {
                switch (line = keyboard.readLine()){
                    case "next":
                        System.out.println(line);
                        out.println(line); // отсылаем введенную строку текста серверу.
                        line = in.readLine(); // ждем пока сервер отошлет строку текста.
                        System.out.println("output number: " + line);
                        break;
                    case "stop":
                    case "exit":
                    case "break":
                        active = false;
                        socket.close();
                        System.out.println("last");
                        break;
                        default:
                            out.println(line); // отсылаем введенную строку текста серверу.
                            line = in.readLine(); // ждем пока сервер отошлет строку текста.
                            System.out.println(line);
                            break;
                }
            }
        } catch (Exception x) {
            switch (x.getMessage()) {
                case "Connection reset":
                    System.out.println(x.getMessage());
                    break;
                case "Connection refused: connect":
                    System.out.println("Connection refused: server connection not established");
                    break;
                default:
                    x.printStackTrace();
                    break;
            }
        }
    }
}