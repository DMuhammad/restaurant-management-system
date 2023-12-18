/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.auth.Register;
import view.auth.Login;
import view.admin.menu.ItemManagement;
import DB.koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import view.admin.MainMenuAdmin;
import view.cashier.OrderPayment;
import view.chef.PendingOrder;
import view.menu.MainMenu;
import view.waiters.PreparedOrder;

/**
 *
 * @author Dzikri
 */
public class UserController {

    private Login loginView;
    private Register registerView;

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rst;
    private User user;

    public UserController(Login loginView) {
        this.loginView = loginView;
        loginView.setVisible(true);
        addEventLogin();
    }

    public UserController(Register registerView) {
        this.registerView = registerView;
        registerView.setVisible(true);
        addEventRegister();
    }

    /**
     * @return the loginView
     */
    public Login getLoginView() {
        return loginView;
    }

    /**
     * @param loginView the loginView to set
     */
    public void setLoginView(Login loginView) {
        this.loginView = loginView;
        loginView.setVisible(true);
    }

    /**
     * @return the registerView
     */
    public Register getRegisterView() {
        return registerView;
    }

    /**
     * @param registerView the registerView to set
     */
    public void setRegisterView(Register registerView) {
        this.registerView = registerView;
        registerView.setVisible(true);
    }

    public void login() {
        String email = loginView.getEmail().getText();
        String password = new String(loginView.getPassword().getPassword());
        String role;
        ResultSet rs;

        try {
            if (email.equals("") || password.equals("")) {
                loginView.showMessage("EMAIL / PASSWORD HARUS DIISI");
            } else if (password.length() < 6) {
                loginView.showMessage("PASSWORD MINIMAL 6 KATA");
            } else {
                rs = new User(email, password).getUserByEmail();
                if (rs != null) {
                    loginView.showMessage("LOGIN BERHASIL");
                    int id = rs.getInt("id");
                    user = new User();
                    user.setId(id);

                    switch (rs.getString("role")) {
                        case "Admin":
                            new MainMenuController(new MainMenuAdmin());
                            loginView.dispose();
                            break;
                        case "Chef":
                            new OrderController(new PendingOrder());
                            loginView.dispose();
                            break;
                        case "Waiter":
                            new OrderController(new PreparedOrder());
                            loginView.dispose();
                            break;
                        case "Cashier":
                            new ReceiptController(new OrderPayment());
                            loginView.dispose();
                            break;
                        default:
                            new MainMenuController(new MainMenu(), user);
                            loginView.dispose();
                            break;
                    }
                } else {
                    loginView.showMessage("AKUN TIDAK DITEMUKAN. SILAHKAN COBA LAGI ATAU DAFTAR TERLEBIH DAHULU.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register() {
        String username = registerView.getUsername().getText();
        String email = registerView.getEmail().getText();
        String password = new String(registerView.getPassword().getPassword());
        String confPassword = new String(registerView.getConfPassword().getPassword());
        String role = "Customer";
        boolean status;

        if (username.equals("") || email.equals("") || password.equals("") || confPassword.equals("")) {
            registerView.showMessage("USERNAME / EMAIL / PASSWORD / CONFIRM PASSWORD HARUS DI ISI");
        } else if (password.length() < 6 || confPassword.length() < 6) {
            registerView.showMessage("PASSWORD / CONFIRM PASSWORD MINIMAL 6 KATA");
        } else if (!confPassword.equals(password)) {
            registerView.showMessage("PASSWORD / CONFIRM PASSWORD HARUS SAMA");
        } else {
            status = new User(username, email, password, role).insert();
            if (status) {
                registerView.showMessage("PENDAFTARAN BERHASIL");
                new UserController(new Login());
                registerView.dispose();

            } else {
                registerView.showMessage("EMAIL SUDAH TERDAFTAR. SILAHKAN LOGIN ATAU GUNAKAN EMAIL LAIN.");
            }
        }
    }

    public void addEventLogin() {
        loginView.getPassword().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginView.getBtnLogin().doClick();
                }
            }
        });
        loginView.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        loginView.getLblExit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                System.exit(0);
            }
        });

        loginView.getLblRegister().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                new UserController(new Register());
                loginView.dispose();
            }
        });
    }

    public void addEventRegister() {
        registerView.getPassword().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    registerView.getBtnRegister().doClick();
                }
            }
        });

        registerView.getBtnRegister().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });

        registerView.getLblExit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                System.exit(0);
            }
        });

        registerView.getLblLogin().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                new UserController(new Login());
                registerView.dispose();
            }
        });
    }
}
