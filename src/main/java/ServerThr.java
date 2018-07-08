import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

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
    public void interrupt() {
        try {
            DB.getInst().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.interrupt();
    }

    @Override
        public void run() {

            try {
                this.sin = s.getInputStream();
                this.sout = s.getOutputStream();

                System.out.println("new conect");
                PrintWriter out = new PrintWriter(sout,true);
                BufferedReader in = new BufferedReader(new InputStreamReader(sin));

                String output, line;

                while ((line = in.readLine()) != null) {


                    database = DB.getInst();

                    switch (line) {
                        case "next":
                            output = String.valueOf(database.getNext());
                            break;
                        default:
                            output = String.valueOf("command not found, try " +
                                    "next - to get nextid");
                            break;
                    }
                    out.println(output);
                }
                s.close();
            }catch(Exception x) {
                if (!x.getMessage().equals("Connection reset")) {
                    x.printStackTrace();
                }
            }

        }

}
