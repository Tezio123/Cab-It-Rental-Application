package com.cabit.Cab_It.database.mysql;

public enum DatabaseAccessCredential
{
    /*
    * An enum class to maintain database connection credentials
    * */
    DRIVER("com.mysql.jdbc.Driver"),
    URL_PREFIX("jdbc:mysql://localhost:3306/"),
    DATABASE("cab_it"),
    URL(URL_PREFIX.getValue().concat(DATABASE.getValue())),
    USER("root"),
    PASSWORD("");

    private String value;

    DatabaseAccessCredential(String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
