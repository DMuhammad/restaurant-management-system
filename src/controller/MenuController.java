/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.admin.menu.UpdateItem;
import view.admin.menu.ItemManagement;
import view.admin.menu.ViewItem;
import view.admin.menu.AddItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import view.admin.MainMenuAdmin;
import model.Menu;

/**
 *
 * @author Dzikri
 */
public class MenuController {

    private AddItem addView;
    private UpdateItem updateView;
    private ViewItem viewItem;
    private ItemManagement itemView;
    private Menu menu;
    private DefaultTableModel model;
    private ResultSet rst;

    public MenuController(AddItem addView) {
        this.addView = addView;
        addView.setVisible(true);
        addEventAddItem();
    }

    public MenuController(UpdateItem updateView) {
        this.updateView = updateView;
        updateView.setVisible(true);
        addEventUpdateItem();
    }

    public MenuController(ViewItem viewItem) {
        this.viewItem = viewItem;
        viewItem.setVisible(true);
        addEventViewItem();
    }

    public MenuController(ItemManagement itemView) {
        this.itemView = itemView;
        itemView.setVisible(true);
        addEventItemManagement();
    }

    public void addEventItemManagement() {
        itemView.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuController(new AddItem());
                itemView.dispose();
            }
        });
        itemView.getViewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuController(new ViewItem());
                itemView.dispose();
            }
        });
        itemView.getModifyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuController(new UpdateItem());
                itemView.dispose();
            }
        });
        itemView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuController(new MainMenuAdmin());
                itemView.dispose();
            }
        });
    }

    public void addEventAddItem() {
        addView.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = addView.getItemNameField().getText();
                String description = addView.getItemDescField().getText();
                Double price = Double.valueOf(addView.getItemPriceField().getText());
                boolean status;

                if (name.equals("") || description.equals("") || price.equals("")) {
                    addView.showMessage("NAME / DESCRIPTION / PRICE HARUS DI ISI");
                } else if (description.length() < 10) {
                    addView.showMessage("DESCRIPTION MINIMAL 10 KARAKTER");
                } else if (price < 2.5) {
                    addView.showMessage("PRICE HARUS BERNILAI >= 2.5");
                } else {
                    status = new Menu().insert(name, description, price);
                    if (status) {
                        addView.showMessage("BERHASIL MENAMBAHKAN MENU");

                        addView.getItemDescField().setText("");
                        addView.getItemNameField().setText("");
                        addView.getItemPriceField().setText("");
                    } else {
                        addView.showMessage("MENU SUDAH ADA.");
                    }
                }
            }
        });
        addView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuController(new ItemManagement());
                addView.dispose();
            }
        });
    }

    public void addEventUpdateItem() {
        menu = new Menu();
        showMenus((DefaultTableModel) updateView.getTableMenu().getModel());
        updateView.getTableMenu().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = updateView.getTableMenu().getSelectedRow();
                    if (selectedRow != -1) {
                        int id = Integer.parseInt(updateView.getTableMenu().getValueAt(selectedRow, 0).toString());
                        String name = updateView.getTableMenu().getValueAt(selectedRow, 1).toString();
                        String description = updateView.getTableMenu().getValueAt(selectedRow, 2).toString();
                        String price = updateView.getTableMenu().getValueAt(selectedRow, 3).toString();

                        menu.setId(id);

                        updateView.getmName().setText(name);
                        updateView.getDescription().setText(description);
                        updateView.getPrice().setText(price);
                    }
                }
            }
        });
        updateView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = updateView.getmName().getText();
                String description = updateView.getDescription().getText();
                Double price = Double.valueOf(updateView.getPrice().getText());

                if (name.equals("") || description.equals("") || price.equals("")) {
                    updateView.showMessage("NAME / DESCRIPTION / PRICE HARUS DI ISI");
                } else if (description.length() < 10) {
                    updateView.showMessage("DESCRIPTION MINIMAL 10 KARAKTER");
                } else if (price < 2.5) {
                    updateView.showMessage("PRICE HARUS BERNILAI >= 2.5");
                } else {
                    boolean status = menu.updateUser(menu.getId(), name, description, price);
                    if (status) {
                        updateView.showMessage("BERHASIL UPDATE MENU");

                        updateView.getmName().setText("");
                        updateView.getDescription().setText("");
                        updateView.getPrice().setText("");

                        showMenus((DefaultTableModel) updateView.getTableMenu().getModel());
                    } else {
                        addView.showMessage("MENU SUDAH ADA.");
                    }
                }

            }
        });
        updateView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuController(new ItemManagement());
                updateView.dispose();
            }
        });
    }

    public void addEventViewItem() {
        menu = new Menu();
        showMenus((DefaultTableModel) viewItem.getTableMenu().getModel());
        viewItem.getTableMenu().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = viewItem.getTableMenu().getSelectedRow();
                    if (selectedRow != -1) {
                        int id = Integer.parseInt(viewItem.getTableMenu().getValueAt(selectedRow, 0).toString());

                        menu.setId(id);
                    }
                }
            }
        });
        viewItem.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.deleteUser(menu.getId());
                showMenus((DefaultTableModel) viewItem.getTableMenu().getModel());
            }
        });
        viewItem.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuController(new ItemManagement());
                viewItem.dispose();
            }
        });
    }

    public void showMenus(DefaultTableModel model) {
        model.setRowCount(0);
        List<Menu> menuList = new Menu().getAllMenuItems();
        for (Menu mn : menuList) {
            model.addRow(new Object[]{
                mn.getId(),
                mn.getName(),
                mn.getDescription(),
                mn.getPrice()
            });
        }
    }
}
