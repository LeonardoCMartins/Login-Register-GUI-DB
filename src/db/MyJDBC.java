package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MyJDBC {

    private static Connection connection;

    public MyJDBC(Connection connection){
        this.connection = connection;
    }


    public static boolean registerUser(String username, String password, String repassword){

        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            if (password.equals(repassword)) {
                st = connection.prepareStatement(
                        "INSERT INTO usuarios "
                                + "(username, password) "
                                + "VALUES "
                                + "(?,?)"

                );

                st.setString(1,username);
                st.setString(2,password);

                st.executeUpdate();

                return true;

            } else {
                System.out.println("error");
                return false;
            }


        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

    }

    public static boolean checkUser(String username){

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = connection.prepareStatement(
                    "SELECT * FROM usuarios WHERE username = ?"
            );

            st.setString(1,username);

            rs = st.executeQuery();

            if(!rs.isBeforeFirst()){
                return false;
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return true;

    }

    public static boolean validateLogin(String username, String password){

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = connection.prepareStatement(
                    "SELECT * FROM usuarios WHERE username = ? AND password = ?"
            );

            st.setString(1,username);
            st.setString(2,password);

            rs = st.executeQuery();

            if(!rs.isBeforeFirst()){
                return false;
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return true;

    }




}
