package edu.neu.madcourse.numad21su_vocab;

public class UserInfo {
    private final String FirstName;
    private final String LastName;
    private final String UserName;

    public UserInfo(String fname, String lname, String uname) {
        this.FirstName = fname;
        this.LastName = lname;
        this.UserName = uname;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getUserName() {
        return UserName;
    }
}
