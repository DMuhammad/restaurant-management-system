/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Order;
import model.Receipt;
import view.cashier.OrderPayment;

/**
 *
 * @author Dzikri
 */
public class ReceiptController {

    private OrderPayment op;
    private Order order;
    private Receipt receipt;

    private DefaultTableModel model;

    public ReceiptController(OrderPayment op) {
        this.op = op;
        op.setVisible(true);
        addEventCashier();
    }

    public void addEventCashier() {
        order = new Order();
        showOrders();
        op.getTabelOrder().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = op.getTabelOrder().getSelectedRow();
                    if (selectedRow != -1) {
                        int id = Integer.parseInt(op.getTabelOrder().getValueAt(selectedRow, 0).toString());
                        int user_id = Integer.parseInt(op.getTabelOrder().getValueAt(selectedRow, 1).toString());
                        String order_item = op.getTabelOrder().getValueAt(selectedRow, 2).toString();
                        String status = op.getTabelOrder().getValueAt(selectedRow, 3).toString();
                        String date = op.getTabelOrder().getValueAt(selectedRow, 4).toString();
                        Double total = Double.parseDouble(op.getTabelOrder().getValueAt(selectedRow, 5).toString());

                        op.getId().setText(String.valueOf(id));
                        op.getUserid().setText(String.valueOf(user_id));
                        op.getOrderitem().setText(order_item);
                        op.getStatus().setText(status);
                        op.getDate().setText(date);
                        op.getTotal().setText(String.valueOf(total));
                    }
                }
            }
        });
        op.getBtn_update().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receipt = new Receipt();

                if (op.getPaymentAmount().getText().equals("")) {
                    op.showMessage("PAYMENT AMOUNT HARUS DIISI.");
                } else if (Double.valueOf(op.getPaymentAmount().getText()) < Double.valueOf(op.getTotal().getText())) {
                    op.showMessage("PAYMENT AMOUNT TIDAK BOLEH KURANG DARI TOTAL");
                } else {
                    String payment_type = op.getPaymentType().getSelectedItem().toString();
                    Double paymentAmount = Double.valueOf(op.getPaymentAmount().getText());
                    Date tanggalHariIni = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
                    String payment_date = dateFormat.format(tanggalHariIni);
                    
                    boolean result = order.updateOrderStatus(Integer.parseInt(op.getId().getText()), "PAID");

                    if (result) {
                        receipt.createReceipt(Integer.parseInt(op.getId().getText()), paymentAmount, payment_date, payment_type);
                        showOrders();
                    } else {
                        op.showMessage("ORDER TIDAK DITEMUKAN.");
                    }
                }
            }
        });
        op.getBtn_back().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void showOrders() {
        model = (DefaultTableModel) op.getTabelOrder().getModel();
        model.setRowCount(0);
        List<Order> orderList = new Order().getAllOrders();

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
