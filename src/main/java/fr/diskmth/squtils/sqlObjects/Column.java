package fr.diskmth.squtils.sqlObjects;

import fr.diskmth.squtils.DataType;

public class Column
{
    /*--------------------------- Class attributes ---------------------------*/

    private String name;
    private DataType dataType;
    private String defaultValue;

    public Column(String name, DataType dataType, String defaultValue)
    {
        this.name = name;
        this.dataType = dataType;
        this.defaultValue = defaultValue;
    }

    public Column(String name, DataType dataType)
    {
        this(name, dataType, "");
    }

    /*--------------------------- Class methods and functions ---------------------------*/

    public String toSQLSyntax()
    {
        return name + " " + dataType.toSQLSyntax() + (defaultValue.equals("") ? "" : " " + defaultValue);
    }

    /*--------------------------- Getters and Setters ---------------------------*/

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public DataType getDataType()
    {
        return dataType;
    }

    public void setDataType(DataType dataType)
    {
        this.dataType = dataType;
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }
}
