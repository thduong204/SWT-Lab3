/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongth.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import duongth.database.DBUtils;


public class RegistrationDAO implements Serializable {

    DBUtils db = new DBUtils();

//    public boolean checkLogin(String username, String password) throws SQLException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//
//        try {
//            con = db.makeConnection();
//            if (con != null) {
//                String sql = "Select * From"
//                        + " Users Where username =? and password = ?";
//                stm = con.prepareStatement(sql);
//                stm.setString(1, username);
//                stm.setString(2, password);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    return true;
//                }
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return false;
//    }
    
    public boolean[] checkLogin(String username, String password) throws SQLException {
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    boolean[] result = {false, false}; // [0]: isValidUser, [1]: isAdmin

    try {
        con = db.makeConnection();
        if (con != null) {
            String sql = "SELECT isAdmin FROM Users WHERE username = ? AND password = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();

            if (rs.next()) {
                result[0] = true; // User tồn tại
                result[1] = rs.getBoolean("isAdmin"); // Lấy giá trị isAdmin
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        if (rs != null) rs.close();
        if (stm != null) stm.close();
        if (con != null) con.close();
    }
    return result;
}

    
    private List<RegistrationDTO> listAccounts;

    public List<RegistrationDTO> getListAccounts() {
        return listAccounts;
    }

    public void searchByLastname(String searchValue) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = db.makeConnection();
            if (con != null) {
                String sql = "Select * From Users Where full_name like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, '%' + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String full_name = rs.getString("full_name");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, full_name, role);
                    if (listAccounts == null) {
                        listAccounts = new ArrayList<>();
                    }
                    listAccounts.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteRecord(String username) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = db.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM Users WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateRecord(String password, String full_name, boolean role, String username) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = db.makeConnection();
            if (con != null) {
                String sql = " Update Users set password=?, full_name=?, isAdmin =? "
                        + "where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, full_name);
                stm.setBoolean(3, role);
                stm.setString(4, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    public boolean insertRecord(String username, String password, String full_name, boolean role) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = db.makeConnection();
            if(con!=null){
                String sql = "insert into Users (username,password, full_name, isAdmin)"
                        + " values (?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, full_name);
                stm.setBoolean(4, role);
                int row = stm.executeUpdate();
                if(row>0){
                    return true;
                }
            }
        } finally {
            if(stm!=null){
                stm.close();
            }
            if(con!=null){
                con.close();
            }
        }
        return false;
    }
    
    public boolean CreateAccount(String Username, String Password, String Fullname) throws SQLException{
       Connection con =null;
       PreparedStatement stm = null;
       try {
           con = db.makeConnection();
           if(con!=null){
               String Sql ="Insert into USERS (USERNAME,PASSWORD,LASTNAME,PHONENUMBER) values(?,?,?,?)";
               stm = con.prepareStatement(Sql);
               stm.setNString(1, Username.trim());
               stm.setString(2, Password);
               stm.setNString(3, Fullname);
               
               int row = stm.executeUpdate();
               if(row>0){
                   return true;
               }
               
           }
       } finally{
           if(stm!=null)
               stm.close();
           if(con!=null)
               con.close();
       }
       return false;
   }
}
