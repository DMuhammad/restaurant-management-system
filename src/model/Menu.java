/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DB.koneksi;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Dzikri
 */
public class Menu {

    private int id;
    private String item;
    private String description;
    private Double price;

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rst;
    
    public Menu(){
        
    }

    public Menu(String item, String description, Double price) {
        this.item = item;
        this.description = description;
        this.price = price;
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
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

    public boolean insert(String name, String description, Double price) {
        boolean result = false;

        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("SELECT name FROM menu WHERE name = '"
                    + name + "'");
            rst = pst.executeQuery();

            if (!rst.next()) {
                pst = conn.prepareStatement("INSERT INTO menu (name, description, price) VALUES ('"
                        + name + "','"
                        + description + "','"
                        + price + "');");
                pst.execute();

                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            result = false;
        }

        return result;
    }
    
    public ResultSet getAllMenuItems() {
        ResultSet rs = null;

        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("SELECT * FROM menu");
            rs = pst.executeQuery();
        } catch (Exception e) {
        }

        return rs;
    }
    
    public boolean updateUser(int id, String name, String description, Double price) {
        boolean result = false;
        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("UPDATE menu SET " 
                    + "name = '" + name + "', "
                    + "description = '" + description + "', "
                    + "price = '" +  price + "', "
                    + "WHERE id = '" + id + "'");
            pst.execute();
            result = true;
        } catch (SQLException e) {
            result = false;
        }
        return result;
    }
    
    public void deleteUser(int id) {
        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("DELETE FROM menu WHERE id = '" + id + "'");
            pst.execute();
        } catch (Exception e) {
        }
    }
}
