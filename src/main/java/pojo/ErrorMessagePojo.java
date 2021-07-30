package pojo;

public class ErrorMessagePojo
{
    private int code;

    private String developerMessage;

    public int getCode ()
    {
        return code;
    }

    public void setCode (int code)
    {
        this.code = code;
    }

    public String getDeveloperMessage ()
    {
        return developerMessage;
    }

    public void setDeveloperMessage (String developerMessage)
    {
        this.developerMessage = developerMessage;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", developerMessage = "+developerMessage+"]";
    }
}
