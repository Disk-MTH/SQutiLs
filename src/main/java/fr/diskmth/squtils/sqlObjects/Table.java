package fr.diskmth.squtils.sqlObjects;

import java.util.List;

public class Table extends SQLObject
{
    // empecher de mettre une table sans aucune colonne

    /*--------------------------- Class attributes ---------------------------*/

    private String name;
    private List<Column> columns;

    /*--------------------------- Class constructor ---------------------------*/

    public Table(String name, List<Column> columns)
    {
        this.name = name;
        this.columns = columns;
    }

    /*--------------------------- Getters and Setters ---------------------------*/

    public String getName()
    {
        return name;
    }

    public List<Column> getColumns()
    {
        return columns;
    }
}
