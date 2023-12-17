/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.admin.menu.UpdateItem;
import view.admin.menu.ItemManagement;
import view.admin.menu.ViewItem;
import view.admin.menu.DeleteItem;
import view.admin.menu.AddItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.*;

/**
 *
 * @author Dzikri
 */
public class MenuController {

    private AddItem addView;
    private DeleteItem deleteView;
    private UpdateItem updateView;
    private ViewItem viewItem;
    private ItemManagement itemView;

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

    public MenuController(DeleteItem deleteView) {
        this.deleteView = deleteView;
        deleteView.setVisible(true);
        addEventDeleteItem();
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
        itemView.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuController(new DeleteItem());
                itemView.dispose();
            }
        });
    }

    public void addEventAddItem() {
        addView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuController(new ItemManagement());
                addView.dispose();
            }
        });
    }

    public void addEventUpdateItem() {
        updateView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuController(new ItemManagement());
                updateView.dispose();
            }
        });
    }

    public void addEventViewItem() {
        viewItem.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuController(new ItemManagement());
                viewItem.dispose();
            }
        });
    }

    public void addEventDeleteItem() {
        deleteView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuController(new ItemManagement());
                deleteView.dispose();
            }
        });
    }
}
