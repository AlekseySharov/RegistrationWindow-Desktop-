package example.desktopapplication;
import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException,SQLException{
String connectionString = "jdbc:mysql://" + dbHost + ":"
        + dbPort + "/" + dbName;
Class.forName("com.mysql.cj.jdbc.Driver");

dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
return dbConnection;
    }
    public void signUpUsers(User user)
    {
String insert = "INSERT INTO " + Const.USER_TABLE +
        "( " + Const.USER_FIRSTNAME + ", " + Const.USER_LASTNAME + ", "
        + Const.USER_USERNAME + ", " + Const.USER_PASSWORD + ", "
        + Const.USER_LOCATION + ", " + Const.USER_GENDER + ") VALUES(?,?,?,?,?,?)";
        try {
        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1, user.getFirstName());
        prSt.setString(2, user.getLastName());
        prSt.setString(3, user.getUserName());
        prSt.setString(4, user.getPassword());
        prSt.setString(5, user.getLocation());
        prSt.setString(6, user.getGender());


            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
public ResultSet getUser (User user)
{
    ResultSet resSet = null;
    String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE "
            + Const.USER_USERNAME + "=? AND " + Const.USER_PASSWORD + "=?";
    try {
        PreparedStatement preStat = getDbConnection().prepareStatement(select);
        preStat.setString(1, user.getUserName());
        preStat.setString(2, user.getPassword());


       resSet = preStat.executeQuery();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    return resSet;
}
}
