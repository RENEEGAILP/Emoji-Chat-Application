package edu.neu.madcourse.numad21su_vocab;

public class UserInfo {
    String firstName;
    String lastName;
    String userName;

    public UserInfo()
    {    }

    public UserInfo(String fname, String lname, String uname) {
        this.firstName = fname;
        this.lastName = lname;
        this.userName = uname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }
}
