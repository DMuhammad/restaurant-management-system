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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dzikri
 */
public class Menu {

    private int id;
    private String name;
    private String description;
    private Double price;

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rst;
    
    public Menu(){
        
    }

    public Menu(int id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
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
    public String getName() {
        return name;
    }

    /**
     * @param item the item to set
     */
    public void setName(String name) {
        this.name = name;
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
    
    public List<Menu> getAllMenuItems() {
        List<Menu> menuList = new ArrayList<>();

        try {
            Connection conn = koneksi.koneksi();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM menu");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                Menu menu = new Menu(id, name, description, price);
                menuList.add(menu);
            }
        } catch (Exception e) {
        }

        return menuList;
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
