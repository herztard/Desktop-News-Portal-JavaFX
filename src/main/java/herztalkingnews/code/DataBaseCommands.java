package herztalkingnews.code;

import java.sql.*;

class DataBaseCommands{
    public static Connection connect() throws SQLException, ClassNotFoundException {
        Connection connection = null;

        try {
            String url = "jdbc:sqlite:D:\\CSS108\\Adilzhan_Slyamgazy_220103151_project_2\\" +
                    "src\\main\\resources\\database\\herztalkingnews.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Connection to database has NOT been established.");
        }

        return connection;
    }
        //==============================

    public static void createTables() throws SQLException, ClassNotFoundException{
        Connection connection = null;

        try {
            String url = "jdbc:sqlite:D:\\CSS108\\Adilzhan_Slyamgazy_220103151_project_2\\" +
                    "src\\main\\resources\\database\\herztalkingnews.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Connection to database has NOT been established.");
        }

        String sql = "CREATE TABLE USERS (" +
                " username TEXT, " +
                " password TEXT, " +
                " email TEXT," +
                " isAdmin INT)";

        try{
            assert connection != null;
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("A new table with users has been created.");

        } catch (SQLException e) {
            System.out.println("Table with users has already created.");
        }


        sql = "CREATE TABLE POSTS (" +
                " id               INT," +
                " header           TEXT," +
                " content          TEXT," +
                " date             TEXT," +
                " image            BLOB)";
        try{
            assert connection != null;
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("A new table with posts has been created.");
        } catch (SQLException e){
            System.out.println("Table with users has already created.");
        }
        connection.close();
    }

    public static void createUser(String username, String password, String confirmPassword, String email) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:D:\\CSS108\\Adilzhan_Slyamgazy_220103151_project_2\\" +
                "src\\main\\resources\\database\\herztalkingnews.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            if (password.equals(confirmPassword)) {
                String sql = "INSERT INTO USERS(username, password, email) VALUES (?,?,?)";

                PreparedStatement prepStatement = connection.prepareStatement(sql);
                prepStatement.setString(1, username);
                prepStatement.setString(2, password);
                prepStatement.setString(3, email);
                prepStatement.executeUpdate();
                prepStatement.close();
                connection.close();
            } else {

            }
        } catch (Exception e){

        }
    }
}
