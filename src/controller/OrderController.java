/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.chef.PendingOrder;
import view.waiters.PreparedOrder;
import javax.swing.table.DefaultTableModel;
import model.Order;

/**
 *
 * @author Dzikri
 */
public class OrderController {

    private PendingOrder pendingOrder;
    private PreparedOrder preparedOrder;
    private Order order;
    private DefaultTableModel model;
    
    public OrderController(PendingOrder pendingOrder) {
        this.pendingOrder = pendingOrder;
        pendingOrder.setVisible(true);
        addEventChef();
    }
    
    public OrderController(PreparedOrder preparedOrder) {
        this.preparedOrder = preparedOrder;
        preparedOrder.setVisible(true);
        addEventWaiters();
    }
    
    public void addEventChef() {
        order = new Order();
        showPendingOrders();
        pendingOrder.getTabelOrder().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = pendingOrder.getTabelOrder().getSelectedRow();
                    if (selectedRow != -1) {
                        int id = Integer.parseInt(pendingOrder.getTabelOrder().getValueAt(selectedRow, 0).toString());
                        int user_id = Integer.parseInt(pendingOrder.getTabelOrder().getValueAt(selectedRow, 1).toString());
                        pendingOrder.getUserid().setText(String.valueOf(user_id));
                        
                        order.setId(id);
                    }
                }
            }
        });
        pendingOrder.getBtn_update().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String status = pendingOrder.getStatus().getSelectedItem().toString();
                boolean result = order.updateOrderStatus(order.getId(), status);
                
                if (result) {
                    pendingOrder.showMessage("BERHASIL MEMPROSES ORDER");
                    showPendingOrders();
                } else {
                    pendingOrder.showMessage("GAGAL MEMPROSES ORDER");
                }
            }
        });
        pendingOrder.getBtn_back().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    public void addEventWaiters() {
        order = new Order();
        showPreparedOrders();
        preparedOrder.getTabelOrder().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = preparedOrder.getTabelOrder().getSelectedRow();
                    if (selectedRow != -1) {
                        int id = Integer.parseInt(preparedOrder.getTabelOrder().getValueAt(selectedRow, 0).toString());
                        int user_id = Integer.parseInt(preparedOrder.getTabelOrder().getValueAt(selectedRow, 1).toString());
                        preparedOrder.getUserid().setText(String.valueOf(user_id));
                        
                        order.setId(id);
                    }
                }
            }
        });
        preparedOrder.getBtn_update().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String status = preparedOrder.getStatus().getSelectedItem().toString();
                boolean result = order.updateOrderStatus(order.getId(), status);
                
                if (result) {
                    preparedOrder.showMessage("BERHASIL MEMPROSES ORDER");
                    showPreparedOrders();
                } else {
                    preparedOrder.showMessage("GAGAL MEMPROSES ORDER");
                }
            }
        });
        preparedOrder.getBtn_back().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    public void showPendingOrders() {
        model = (DefaultTableModel) pendingOrder.getTabelOrder().getModel();
        model.setRowCount(0);
        List<Order> orderList = new Order().getAllPendingOrders();
        
        for (Order o : orderList) {
            model.addRow(new Object[]{
                o.getId(),
                o.getUser_id(),
                o.getOrder_item(),
                o.getStatus(),
                o.getDate(),
                o.getTotal()
            });
        }
    }
    
    public void showPreparedOrders() {
        model = (DefaultTableModel) preparedOrder.getTabelOrder().getModel();
        model.setRowCount(0);
        List<Order> orderList = new Order().getAllPreparedOrders();
        
        for (Order o : orderList) {
            model.addRow(new Object[]{
                o.getId(),
                o.getUser_id(),
                o.getOrder_item(),
                o.getStatus(),
                o.getDate(),
                o.getTotal()
            });
        }
    }
}
