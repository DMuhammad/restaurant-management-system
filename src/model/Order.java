/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DB.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dzikri
 */
public class Order {

    private int id;
    private int user_id;
    private String order_item;
    private String status;
    private String date;
    private Double total;

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rst;

    public Order() {

    }
    
    public Order(int id, int user_id, String order_item, String status, String date, Double total) {
        this.id = id;
        this.user_id = user_id;
        this.order_item = order_item;
        this.status = status;
        this.date = date;
        this.total = total;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the order_item
     */
    public String getOrder_item() {
        return order_item;
    }

    /**
     * @param order_item the order_item to set
     */
    public void setOrder_item(String order_item) {
        this.order_item = order_item;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    public boolean createOrder(int user_id, String order_item, String status, String date, Double total) {
        boolean result = false;
        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("INSERT INTO `order` (user_id, order_item, status, date, total) VALUES ('"
                    + user_id + "','"
                    + order_item + "','"
                    + status + "','"
                    + date + "','"
                    + total + "');");
            pst.execute();
            result = true;
        } catch (SQLException e) {
        }
        
        return result;
    }
    
    public List<Order> getAllPendingOrders() {
        List<Order> orderList = new ArrayList<>();

        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("SELECT * FROM `order` WHERE status = 'PENDING'");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String order_item = rs.getString("order_item");
                String status = rs.getString("status");
                String date = rs.getString("date");
                double total = rs.getDouble("total");
                Order order = new Order(id, user_id, order_item, status, date, total);
                orderList.add(order);
            }
        } catch (Exception e) {
        }

        return orderList;
    }
    
    public List<Order> getAllPreparedOrders() {
        List<Order> orderList = new ArrayList<>();

        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("SELECT * FROM `order` WHERE status = 'PREPARED'");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String order_item = rs.getString("order_item");
                String status = rs.getString("status");
                String date = rs.getString("date");
                double total = rs.getDouble("total");
                Order order = new Order(id, user_id, order_item, status, date, total);
                orderList.add(order);
            }
        } catch (Exception e) {
        }

        return orderList;
    }
    
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();

        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("SELECT * FROM `order`");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String order_item = rs.getString("order_item");
                String status = rs.getString("status");
                String date = rs.getString("date");
                double total = rs.getDouble("total");
                Order order = new Order(id, user_id, order_item, status, date, total);
                orderList.add(order);
            }
        } catch (Exception e) {
        }

        return orderList;
    }
    
    public List<Order> getOrderByUserId(int uid) {
        List<Order> orderList = new ArrayList<>();

        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("SELECT * FROM `order` WHERE user_id = '" + uid + "'");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String order_item = rs.getString("order_item");
                String status = rs.getString("status");
                String date = rs.getString("date");
                double total = rs.getDouble("total");
                Order order = new Order(id, user_id, order_item, status, date, total);
                orderList.add(order);
            }
        } catch (Exception e) {
        }

        return orderList;
    }
    
    public boolean updateOrderStatus(int id, String status) {
        boolean result = false;
        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("UPDATE `order` SET status = '"
                    + status + "' WHERE id = '"
                    + id + "'");
            pst.execute();
            result = true;
        } catch (SQLException e) {
        }
        return result;
    }
}
