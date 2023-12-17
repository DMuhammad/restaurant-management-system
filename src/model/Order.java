/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dzikri
 */
public class Order {
    private String menu_item;
    private int quantity;
    private String status;
    private String payment_type;
    private Double payment_amount;
    
    public Order(String menu_item, int quantity, String status) {
        this.menu_item = menu_item;
        this.quantity = quantity;
        this.status = status;
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

    /**
     * @return the payment_amount
     */
    public Double getPayment_amount() {
        return payment_amount;
    }

    /**
     * @param payment_amount the payment_amount to set
     */
    public void setPayment_amount(Double payment_amount) {
        this.payment_amount = payment_amount;
    }
    
    
}
