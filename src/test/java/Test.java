import fr.diskmth.squtils.Colors;
import fr.diskmth.squtils.DatabaseTypes;
import fr.diskmth.squtils.sqlObjects.Database;

import java.sql.SQLException;

public class Test
{
    public static Database a = new Database(DatabaseTypes.MYSQL, "localhost", 3306, "paradisia_cryptos", "root", "9I7akQxrHd1XAevVNtSp");

    public static void main(String[] args) throws SQLException
    {
        a.connect();
        System.out.println(a.isConnected());
    }
}
