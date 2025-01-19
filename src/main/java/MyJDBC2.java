import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(
                    url:"jdbc:mysql;//3306/mijn_database",
                    user_id: "Root",
                    wachtwoord_hash: 'wachtwoord'
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql:"SELECT * FROM USERS");

            while(resultSet.next()) {
                System.out.println(resultSet.getString(columnLabel:"gebruikersnaam"));
                System.out.println(resultSet.getString(columnLabel:"wachtwoord"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
