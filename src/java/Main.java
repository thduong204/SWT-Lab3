
import java.sql.Connection;
import duongth.database.DBUtils;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Truong
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        DBUtils db = new DBUtils();
        Connection con = db.makeConnection(); 
        if(con!=null){
            System.out.println("Connected ");
        }else{
            System.out.println("Not connect try again");
        }
    }
}
