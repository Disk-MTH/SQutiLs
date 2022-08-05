package fr.diskmth.squtils;

public enum DatabaseTypes
{
    MYSQL("mysql"),
    MARIADB("mariadb"),
    SQLITE("sqlite"),
    MONGODB("mongo");

    private final String connector;
    DatabaseTypes(String connector)
    {
        this.connector = connector;
    }

    public String getConnector()
    {
        return connector;
    }
}
