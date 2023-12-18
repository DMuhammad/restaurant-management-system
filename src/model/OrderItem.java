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

/**
 *
 * @author Dzikri
 */
public class OrderItem {
    private int id;
    private String menu_item;
    private Double price;
    private int quantity;
    
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rst;
    
    public OrderItem() {
        
    }
    
    public OrderItem(String menu_item, Double price, int quantity) {
        this.menu_item = menu_item;
        this.price = price;
        this.quantity = quantity;
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
     * @return the menu_item
     */
    public String getMenu_item() {
        return menu_item;
    }

    /**
     * @param menu_item the menu_item to set
     */
    public void setMenu_item(String menu_item) {
        this.menu_item = menu_item;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public boolean createOrderItem(String menu_item, int quantity) {
        boolean result = false;
        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("INSERT INTO orderitem (menu_item, quantity) VALUES ('"
                    + menu_item + "','"
                    + quantity + "');");
            pst.execute();
            result = true;
        } catch (SQLException e) {
        }
        
        return result;
    }
}
