import java.sql.*;
import org.sqlite.JDBC;

public class DB {

    private static final String CON_STR = "jdbc:sqlite:/tmp/data/database.db";

    private static DB inst = null;

    public static synchronized DB getInst() {
        if (inst == null) {
            inst = new DB();
        }
        return inst;
    }

    private DB() {

        try  {
            DriverManager.registerDriver(new JDBC());
            Connection connection = DriverManager.getConnection(CON_STR);
            Statement statement = connection.createStatement();

            try (PreparedStatement prstatement = connection.prepareStatement(
                    "CREATE TABLE if not exists nextid (id INTEGER PRIMARY KEY NOT NULL, number INTEGER );")) {
                prstatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement.executeUpdate("INSERT OR IGNORE INTO nextid VALUES(1,0);");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Cannot connect to DB");
            System.exit(-1);
        }
    }

    public synchronized String  getNext() {

        try (Statement statement = DriverManager.getConnection(CON_STR).createStatement()){
            while(DriverManager.getConnection(CON_STR).isClosed());

            statement.executeUpdate("update nextid set number = number + 1 where id = 1;");
            ResultSet resultSet = statement.executeQuery("select * from nextid");

            String out =  resultSet.next() ? String.valueOf(resultSet.getInt(2)) : "error";

            return out;
        } catch (SQLException e) {
            e.printStackTrace();

            return e.getMessage();
        }
    }
}
