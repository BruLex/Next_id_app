import java.io.*;
import java.net.Socket;

public class ServerThr extends Thread {
        private Socket s;
        private InputStream sin;
        private OutputStream sout;
        private DB database;

        public ServerThr(Socket s)  {
            this.s = s;
            this.database = DB.getInst();
        }

    @Override
        public void run() {

            try {
                this.sin = s.getInputStream();
                this.sout = s.getOutputStream();

                System.out.println("new connect");
                PrintWriter out = new PrintWriter(sout,true);
                BufferedReader in = new BufferedReader(new InputStreamReader(sin));

                String line;

                if ((line = in.readLine()) != null) {
                    switch (line) {
                        case "next":
                            out.println(database.getNext() + "   hostname:"+this.s.getInetAddress().getHostAddress());
                            break;
                        default:
                            out.println("command not found, try " +
                                    "next - to get nextid");
                            break;
                    }
                }
                DB.getInst().close();
                s.close();
            }catch(Exception x) {
                if (!x.getMessage().equals("Connection reset")) {
                    x.printStackTrace();
                }
            }

        }

}
