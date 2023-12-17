/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DB.koneksi;
import controller.MenuController;
import controller.UserController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import view.admin.menu.ItemManagement;
import view.auth.Login;

/**
 *
 * @author Dzikri
 */
public class User {

    private String username;
    private String email;
    private String password;
    private String role;

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rst;

    public User() {

    }

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    public boolean insert() {
        boolean result = false;

        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("SELECT email FROM user WHERE email = '"
                    + email + "'");
            rst = pst.executeQuery();

            if (!rst.next()) {
                pst = conn.prepareStatement("INSERT INTO user (username, email, password, role) VALUES ('"
                        + username + "','"
                        + email + "','"
                        + password + "','"
                        + role + "');");
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

    public String getUserByEmail() {
        String result = null;

        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("SELECT email, password, role FROM user WHERE email = '"
                    + email + "'");
            rst = pst.executeQuery();

            if (rst.next()) {
                if (password.equals(rst.getString("password"))) {
                    result = rst.getString("role");
                } else {
                    result = null;
                }
            } else {
                result = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }
}
