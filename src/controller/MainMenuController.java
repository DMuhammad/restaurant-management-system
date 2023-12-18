/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.User;
import view.admin.MainMenuAdmin;
import view.admin.menu.*;
import view.admin.user.UserManagement;
import view.menu.MainMenu;
import view.MenuItems;
import view.OrderedMenus;
/**
 *
 * @author Dzikri
 */
public class MainMenuController {
    private MainMenuAdmin mainView;
    private MainMenu mainMenuView;
    private User user;
    
    public MainMenuController(MainMenuAdmin mainView) {
        this.mainView = mainView;
        mainView.setVisible(true);
        addEventMainMenu();
    }
    
    public MainMenuController(MainMenu mainMenuView, User user) {
        this.mainMenuView = mainMenuView;
        this.user = user;
        mainMenuView.setVisible(true);
        addEventMainMenuView();
    }
    
    public void addEventMainMenu() {
        mainView.getItemManagementButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuController(new ItemManagement());
                mainView.dispose();
            }
        });
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
    
    public void addEventMainMenuView() {
        mainMenuView.getMenuItemsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderItemController(new MenuItems(), user);
                mainMenuView.dispose();
            }
        });
        mainMenuView.getOrderedMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderItemController(new OrderedMenus(), user);
                mainMenuView.dispose();
            }
        });
        mainMenuView.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
