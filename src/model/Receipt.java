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
public class Receipt {
    private int id;
    private int order_id;
    private double payment_amount;
    private String payment_date;
    private String payment_type;
    
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rst;
    
    public Receipt() {
        
    }
    
    public Receipt(int id, int order_id, double payment_amount, String payment_date, String payment_type) {
        this.id = id;
        this.order_id = order_id;
        this.payment_amount = payment_amount;
        this.payment_date = payment_date;
        this.payment_type = this.payment_type;
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
     * @return the order_id
     */
    public int getOrder_id() {
        return order_id;
    }

    /**
     * @param order_id the order_id to set
     */
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    /**
     * @return the payment_amount
     */
    public double getPayment_amount() {
        return payment_amount;
    }

    /**
     * @param payment_amount the payment_amount to set
     */
    public void setPayment_amount(double payment_amount) {
        this.payment_amount = payment_amount;
    }

    /**
     * @return the payment_date
     */
    public String getPayment_date() {
        return payment_date;
    }

    /**
     * @param payment_date the payment_date to set
     */
    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    /**
     * @return the payment_type
     */
    public String getPayment_type() {
        return payment_type;
    }

    /**
     * @param payment_type the payment_type to set
     */
    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }
    
    public void createReceipt(int order_id, Double payment_amount, String payment_date, String payment_type) {
        try {
            conn = koneksi.koneksi();
            pst = conn.prepareStatement("INSERT INTO receipt (order_id, payment_amount, payment_date, payment_type) VALUES ('"
                    + order_id + "','"
                    + payment_amount + "','"
                    + payment_date + "','"
                    + payment_type + "');");
            pst.execute();
        } catch (SQLException e) {
        }
    }
}
