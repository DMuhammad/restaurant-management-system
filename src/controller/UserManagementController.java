/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import view.admin.user.UserManagement;
import model.User;
import view.admin.MainMenu;

/**
 *
 * @author Dzikri
 */
public class UserManagementController {

    private UserManagement userManagement;
    private DefaultTableModel model;
    private ResultSet rst;
    private User user;

    public UserManagementController(UserManagement userManagement) {
        this.userManagement = userManagement;
        userManagement.setVisible(true);
        addEventUserManagement();
    }

    public void addEventUserManagement() {
        user = new User();
        showUser();
        userManagement.getTabeltransaksi().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = userManagement.getTabeltransaksi().getSelectedRow();
                    if (selectedRow != -1) {
                        int id = Integer.parseInt(userManagement.getTabeltransaksi().getValueAt(selectedRow, 0).toString());
                        String email = userManagement.getTabeltransaksi().getValueAt(selectedRow, 2).toString();
                        userManagement.getJtf_email().setText(email);
                        
                        user.setId(id);
                    }
                }
            }
        });
        userManagement.getBtn_update().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = userManagement.getJtf_email().getText();
                String role = userManagement.getRole().getSelectedItem().toString();

                user.setEmail(email);
                user.setRole(role);

                user.updateUser();
                showUser();
            }
        });
        userManagement.getHapus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.deleteUser();
                showUser();
            }
        });
        userManagement.getBtn_back().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuController(new MainMenu());
                userManagement.dispose();
            }
        });
    }

    public void showUser() {
        model = (DefaultTableModel) userManagement.getTabeltransaksi().getModel();
        model.setRowCount(0);
        try {
            rst = new User().getAllUsers();
            while (rst.next()) {
                model.addRow(new Object[]{
                    rst.getString("id"),
                    rst.getString("username"),
                    rst.getString("email"),
                    rst.getString("role")
                });
            }
        } catch (SQLException ex) {
        }
    }
}
