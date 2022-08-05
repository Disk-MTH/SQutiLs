package fr.diskmth.squtils.sqlObjects;

import fr.diskmth.squtils.DataType;
import fr.diskmth.squtils.DatabaseTypes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database extends SQLObject
{
    /*--------------------------- Attributes ---------------------------*/

    private DatabaseTypes type;
    private String url;
    private int port;
    private String name;
    private String user;
    private String password;

    private Connection connection;
    private boolean isConnected = false;

    /*--------------------------- Constructors ---------------------------*/

    public Database(DatabaseTypes type, String url, int port, String name, String user, String password)
    {
        this.type = type == null ? DatabaseTypes.MYSQL : type;
        this.url = url == null ? "" : url;
        this.port = port == 0 ? 3306 : port;
        this.name = name == null ? "" : name;
        this.url = user == null ? "" : user;
        this.password = password == null ? "" : password;
    }

    /*--------------------------- Database connection methods ---------------------------*/

    public void connect() throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:" + type.getConnector() + "://" + url + ":" + port + "/" + name, user, password);
    }
    //connection = DriverManager.getConnection("jdbc:mysql://" + url.trim() + ":" + port + "/" + name.trim(), user.trim(), password.trim());

    public void disconnect()
    {
        connection = null;
        isConnected = false;
    }

    /*--------------------------- Tables interactions methods ---------------------------*/

    public Table getTable(String name) throws SQLException
    {
        if (isConnected())
        {
            if (getConnection().getMetaData().getTables(null, null, name, null).next())
            {
                final List<Column> columns = new ArrayList<>();
                for (int i = 0; i <= getConnection().createStatement().executeQuery("select * from " + name).getMetaData().getColumnCount(); i++)
                {
                    /*if (getConnection().createStatement().executeQuery("select * from " + name).getMetaData().)
                    {

                    }
                    else
                    {
                        columns.add(new Column(getConnection().createStatement().executeQuery("select * from " + name).getMetaData().getColumnName(i), DataType.fromSQLSyntax(getConnection().createStatement().executeQuery("select * from " + name).getMetaData().getColumnTypeName(i))));
                    }*/
                }
                //getConnection().createStatement().executeQuery("select * from " + name).getMetaData().;
                return new Table(name, columns);
            }
        }
        throw new SQLException("The database is not connected. You need to connect it before you can interact with it");
    }

    public void addTable(Table table) throws SQLException
    {
        if (isConnected())
        {
            final StringBuilder columnsAsString = new StringBuilder();

            for (Column column : table.getColumns())
            {
                columnsAsString.append(column.toSQLSyntax()).append(", ");
            }

            columnsAsString.delete(columnsAsString.length() - 2, columnsAsString.length());
            getConnection().createStatement().execute("create table " + table.getName() + "(" + columnsAsString + ");");
            addChild(table);
            return;
        }
        throw new SQLException("The database is not connected. You need to connect it before you can interact with it");
    }

    public void addTables(List<Table> tables) throws SQLException
    {
        for (Table table : tables)
        {
            addTable(table);
        }
    }

    public void removeTable(String name) throws SQLException
    {
        if (isConnected())
        {
            if (getConnection().getMetaData().getTables(null, null, name, null).next())
            {
                getConnection().createStatement().execute("drop table " + name + ";");
                return;
            }
        }
        throw new SQLException("The database is not connected. You need to connect it before you can interact with it");
    }

    public void removeTables(List<String> names) throws SQLException
    {
        for (String name : names)
        {
            removeTable(name);
        }
    }



    /*--------------------------- Columns interactions ---------------------------*/

    /*public ActionResult renameColumn(String oldName, String newName)
    {
        try
        {
            connection.createStatement().execute("alter table " + tableName + ";");
            return ActionResult.SUCCESS;
        }
        catch (SQLException exception)
        {
            return ActionResult.FAIL;
        }
    }*/

    /*--------------------------- Getters and Setters ---------------------------*/

    public boolean isConnected()
    {
        return isConnected;
    }

    public DatabaseTypes getType()
    {
        return type;
    }

    public Database setType(DatabaseTypes type)
    {
        this.type = type;
        return this;
    }

    public String getUrl()
    {
        return url;
    }

    public Database setUrl(String url)
    {
        this.url = url;
        return this;
    }

    public int getPort()
    {
        return port;
    }

    public Database setPort(int port)
    {
        this.port = port;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public Database setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getUser()
    {
        return user;
    }

    public Database setUser(String user)
    {
        this.user = user;
        return this;
    }

    public String getPassword()
    {
        return password;
    }

    public Database setPassword(String password)
    {
        this.password = password;
        return this;
    }

    public Connection getConnection()
    {
        return connection;
    }

    public List<Object> getDatabaseInfo()
    {
        return Arrays.asList(type, url, port, name, user, password);
    }

    public void setDatabaseInfo(DatabaseTypes type, String url, int port, String name, String user, String password)
    {
        this.setType(type);
        this.setUrl(url);
        this.setPort(port);
        this.setName(name);
        this.setUser(user);
        this.setPassword(password);
    }
}
