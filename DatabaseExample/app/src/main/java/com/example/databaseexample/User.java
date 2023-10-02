package com.example.databaseexample;

public class User
{
    private String fname;
    private String lname;
    private String uname;

    public User()
    {

    }

    public User(String f, String l, String u)
    {
        fname = f;
        lname = l;
        uname = u;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String f)
    {
        fname = f;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String l)
    {
        lname = l;
    }

    public String getUname()
    {
        return uname;
    }

    public void setUname(String u)
    {
        uname = u;
    }
}
