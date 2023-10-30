package com.benmacleod.userregistration_homework03;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String age;
    private String password;

    // Base constructor
    public User()
    {

    }

    public User(String u, String f, String l, String e, String p, String a)
    {
        username = u;
        firstName = f;
        lastName = l;
        email = e;
        password = p;
        age = a;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
