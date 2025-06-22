package duongth.registration;

import duongth.database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ttndu
 */
public class BikeDAO {
    private Connection conn;
    DBUtils db = new DBUtils();

    public BikeDAO(Connection conn) {
        this.conn = conn;
    }

    public BikeDAO() {
    try {
        conn = db.makeConnection(); // Giả sử bạn có lớp `DatabaseUtils`
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public List<Bike> getAllBikes() {
    List<Bike> bikes = new ArrayList<>();
    String query = "SELECT id, name, category, price, stock FROM Bike";
    
    try (PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
         
        while (rs.next()) {
            Bike bike = new Bike(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getDouble("price"),
                rs.getInt("stock")
            );
            bikes.add(bike);
        }
    } catch (SQLException e) { 
        e.printStackTrace();
    }
    return bikes;
}


    public List<Bike> searchByBikeName(String name) throws SQLException {
    List<Bike> bikes = new ArrayList<>();
    String query = "SELECT * FROM Bike WHERE name LIKE ?";
    PreparedStatement stmt = conn.prepareStatement(query);
    stmt.setString(1, "%" + name + "%");
    ResultSet rs = stmt.executeQuery();

    while (rs.next()) {
        Bike bike = new Bike(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("category"),
            rs.getDouble("price"),
            rs.getInt("stock")
        );
        bikes.add(bike);
    }
    return bikes;
}
    
    
    public void updateBike(int id, String name, String category, int stock, double price) {
    String sql = "UPDATE Bike SET name = ?, category = ?, stock = ?, price = ? WHERE id = ?";
    try (Connection conn = db.makeConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, name);
        stmt.setString(2, category);
        stmt.setInt(3, stock);
        stmt.setDouble(4, price);
        stmt.setInt(5, id);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public boolean deleteBike(int id) {
    String sql = "DELETE FROM Bike WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

public void addBike(String name, String category, int stock, double price) {
    String sql = "INSERT INTO Bike (name, category, stock, price) VALUES (?, ?, ?, ?)";
    try (Connection conn = db.makeConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, name);
        stmt.setString(2, category);
        stmt.setInt(3, stock);
        stmt.setDouble(4, price);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


}
