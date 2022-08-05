package fr.diskmth.squtils;

import java.util.ArrayList;
import java.util.List;

public class DataType
{
    private static final List<DataType> dataTypes = new ArrayList<>();
    private final String sqlSyntax;

    protected DataType(String sqlSyntax)
    {
        this.sqlSyntax = sqlSyntax;
        dataTypes.add(this);
    }

    public String toSQLSyntax()
    {
        return sqlSyntax;
    }

    public static DataType fromSQLSyntax(String sqlSyntax)
    {
        for (DataType dataType : DataType.dataTypes)
        {
            if (dataType.toSQLSyntax().equals(sqlSyntax))
            {
                return dataType;
            }
        }
        return DataTypes.UNKNOWN;
    }
}
