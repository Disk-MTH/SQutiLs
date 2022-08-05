package fr.diskmth.squtils.sqlObjects;

import fr.diskmth.squtils.Colors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SQLObject
{
    /*--------------------------- Attributes ---------------------------*/

    private SQLObject parent = null;
    private final List<SQLObject> children = new ArrayList<>();

    /*--------------------------- Parent methods ---------------------------*/

    public void setParent(SQLObject parent)
    {
        this.parent = parent;
    }

    public SQLObject getParent()
    {
        return parent;
    }

    public void changeParent(SQLObject parent)
    {
        getParent().children.remove(this);
        setParent(parent);
    }

    public void setParentOf(SQLObject child)
    {
        addChild(child);
        child.setParent(this);
    }

    public void unsetParentOf(SQLObject child)
    {
        removeChild(child);
        child.setParent(null);
    }

    public boolean isParentOf(SQLObject object)
    {
        return children.contains(object);
    }

    /*--------------------------- Children methods ---------------------------*/

    public List<SQLObject> getChildren()
    {
        return children;
    }

    public void addChild(SQLObject child)
    {
        if (children.contains(child))
        {
            if (!children.get(children.lastIndexOf(child)).equals(child))
            {
                children.remove(child);
                children.add(child);
                child.setParent(this);
            }
            System.out.println(Colors.RED + "The object with these attributes: " + Colors.BLUE + Arrays.toString(child.getClass().getFields()) + Colors.RED + " is already a child of the object with these attributes: " + Colors.PURPLE + Arrays.toString(getClass().getFields()) + Colors.RESET);
        }
        children.add(child);
        child.setParent(this);
    }

    public void addChildren(List<SQLObject> children)
    {
        for (SQLObject child : children)
        {
            addChild(child);
        }
    }

    public void removeChild(SQLObject child)
    {
        children.remove(child);
        child.setParent(null);
    }

    public void removeChildren(List<SQLObject> children)
    {
        for (SQLObject child : children)
        {
            removeChild(child);
        }
    }

    public void setChildOf(SQLObject parent)
    {
        parent.addChild(this);
        setParent(parent);
    }

    public void unsetChildOf()
    {
        parent.removeChild(this);
        setParent(null);
    }

    public boolean isChildOf(SQLObject object)
    {
        return object.equals(parent);
    }
}
