package pojo;

public class BodyAnalysis
{
    private int characters;

    private int parts;

    private boolean unicode;

    public int getCharacters ()
    {
        return characters;
    }

    public void setCharacters (int characters)
    {
        this.characters = characters;
    }

    public int getParts ()
    {
        return parts;
    }

    public void setParts (int parts)
    {
        this.parts = parts;
    }

    public boolean getUnicode ()
    {
        return unicode;
    }

    public void setUnicode (boolean unicode)
    {
        this.unicode = unicode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [characters = "+characters+", parts = "+parts+", unicode = "+unicode+"]";
    }
}