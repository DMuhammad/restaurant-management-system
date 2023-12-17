/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.admin.MainMenu;
import view.admin.menu.*;
import view.admin.user.UserManagement;
/**
 *
 * @author Dzikri
 */
public class MainMenuController {
    private MainMenu mainView;
    private ItemManagement itemMenuView;
    
    public MainMenuController(MainMenu mainView) {
        this.mainView = mainView;
        mainView.setVisible(true);
        addEventMainMenu();
    }
    
    public void addEventMainMenu() {
        mainView.getItemManagementButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuController(new ItemManagement());
                mainView.dispose();
            }
        });
//        mainView.getOrderManagementButton().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new MenuController(new ViewItem());
//                mainView.dispose();
//            }
//        });
        mainView.getUserManagementButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserManagementController(new UserManagement());
                mainView.dispose();
            }
        });
        mainView.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
