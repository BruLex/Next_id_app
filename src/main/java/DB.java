import java.sql.*;
import org.sqlite.JDBC;

public class DB {

    private static final String CON_STR = "jdbc:sqlite:./database.db";

    private static DB inst = null;
    private Connection connection;
    private Statement statement;

    public static DB getInst() {
        if (inst == null) {
            inst = new DB();
        }
        return inst;
    }

    private DB() {

        try  {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(CON_STR);
            statement = connection.createStatement();

            try (PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE if not exists nextid (id INTEGER PRIMARY KEY NOT NULL, number INTEGER );")) {
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement.executeUpdate("INSERT OR IGNORE INTO nextid VALUES(1,0);");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Cannot connect to DB");
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String  getNext() {

        try {

            statement.executeUpdate("update nextid set number = number + 1 where id = 1;");
            ResultSet resultSet = statement.executeQuery("select * from nextid");

            return resultSet.next() ? String.valueOf(resultSet.getInt(2)) : "error";

        } catch (SQLException e) {
            e.printStackTrace();

            return e.getMessage();
        }
    }

    public void close() throws SQLException { statement.close(); connection.close(); }
}
