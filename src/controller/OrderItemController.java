/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.MenuItems;
import view.OrderedMenus;
import view.menu.MainMenu;
import model.User;
import javax.swing.table.DefaultTableModel;
import model.Menu;
import model.OrderItem;
import model.Order;

/**
 *
 * @author Dzikri
 */
public class OrderItemController {

    private OrderedMenus orderedMenus;
    private MenuItems mi;
    private User user;
    private OrderItem orderItem;
    private Order order;
    private DefaultTableModel model;
    private Double price;
    private List<OrderItem> cartList;
    private Double totalPrice;
    Date tanggalHariIni;

    public OrderItemController(MenuItems mi, User user) {
        this.mi = mi;
        this.user = user;
        mi.setVisible(true);
        addEventMenuItemsView();
    }
    
    public OrderItemController(OrderedMenus orderedMenus, User user) {
        this.orderedMenus = orderedMenus;
        this.user = user;
        orderedMenus.setVisible(true);
        addEventOrderedMenusView();
    }

    public void addEventMenuItemsView() {
        cartList = new ArrayList<>();
        totalPrice = 0.0;
        showMenus();
        showCarts();
        mi.getjTable1().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = mi.getjTable1().getSelectedRow();
                    if (selectedRow != -1) {
                        String menu_item = mi.getjTable1().getValueAt(selectedRow, 1).toString();
                        price = Double.valueOf(mi.getjTable1().getValueAt(selectedRow, 3).toString());
                        mi.getItemNameToOrderField().setText(menu_item);
                    }
                }
            }
        });
        mi.getAddToCartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = mi.getItemNameToOrderField().getText();
                String quantity = mi.getItemOrderQuantityField().getText();

                if (quantity.equals("") || quantity.equals(0)) {
                    mi.showMessage("QUANTITY HARUS DI ISI DAN TIDAK BOLEH BERNILAI 0.");
                } else {
                    orderItem = new OrderItem(item, price, Integer.parseInt(quantity));
                    mi.getItemNameToOrderField().setText("");
                    mi.getItemOrderQuantityField().setText("");
                    cartList.add(orderItem);
                    showCarts();
                }
            }
        });
        mi.getClearCartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mi.getjTable2().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            int selectedRow = mi.getjTable2().getSelectedRow();
                            if (selectedRow != -1) {
                                cartList.remove(selectedRow);
                                showCarts();
                            }
                        }
                    }
                });
            }
        });
        mi.getOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result;
                order = new Order();

                StringBuilder menuItems = new StringBuilder();

                for (OrderItem oi : cartList) {
                    String menuItem = oi.getMenu_item();
                    totalPrice += oi.getPrice() * oi.getQuantity();
                    if (menuItem != null) {
                        menuItems.append(menuItem).append(", "); // Tambahkan menu_item ke StringBuilder
                    }
                }

                if (menuItems.length() > 0) {
                    menuItems.delete(menuItems.length() - 2, menuItems.length());
                }

                tanggalHariIni = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
                String tanggalTerformat = dateFormat.format(tanggalHariIni);

                result = order.createOrder(user.getId(), menuItems.toString(), "PENDING", tanggalTerformat, totalPrice);

                if (result) {
                    orderItem = new OrderItem();
                    boolean res = false;
                    for (OrderItem oi : cartList) {
                        res = orderItem.createOrderItem(oi.getMenu_item(), oi.getQuantity());
                    }

                    if (res) {
                        mi.showMessage("BERHASIL MEMESAN.");
                        model = (DefaultTableModel) mi.getjTable2().getModel();
                        model.setRowCount(0);
                    }
                } else {
                    mi.showMessage("PEMESANAN GAGAL.");
                }

            }
        });
        mi.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuController(new MainMenu(), user);
                mi.dispose();
            }
        });
    }
    
    public void addEventOrderedMenusView() {
        showOrders();
        orderedMenus.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuController(new MainMenu(), user);
                orderedMenus.dispose();
            }
        });
    }

    public void showMenus() {
        model = (DefaultTableModel) mi.getjTable1().getModel();
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
    
    public void showOrders() {
        model = (DefaultTableModel) orderedMenus.getjTable1().getModel();
        model.setRowCount(0);
        List<Order> orderList = new Order().getOrderByUserId(user.getId());
        
        for (Order o : orderList) {
            model.addRow(new Object[]{
                o.getId(),
                o.getOrder_item(),
                o.getStatus(),
                o.getDate(),
                o.getTotal()
            });
        }
    }

    public void showCarts() {
        model = (DefaultTableModel) mi.getjTable2().getModel();
        model.setRowCount(0);

        for (OrderItem oi : cartList) {
            model.addRow(new Object[]{
                oi.getMenu_item(),
                oi.getQuantity()
            });
        }
    }
}
