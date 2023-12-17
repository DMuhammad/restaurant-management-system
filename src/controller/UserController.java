/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

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
import view.*;

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

        try {
            if (email.equals("") || password.equals("")) {
                registerView.showMessage("EMAIL / PASSWORD");
            } else if (password.length() < 6) {
                registerView.showMessage("PASSWORD MINIMAL 6 KATA");
            } else {
                conn = koneksi.koneksi();
                pst = conn.prepareStatement("SELECT email, password, role FROM user WHERE email = '"
                        + email + "'");
                rst = pst.executeQuery();

                if (rst.next()) {
                    if (password.equals(rst.getString("password"))) {
                        user = new User(rst.getString("email"), rst.getString("role"));
                        loginView.showMessage("LOGIN BERHASIL");
                        
                        if ("Admin".equals(user.getRole())) {
                            new MenuController(new ItemManagement());
                            loginView.dispose();
                        }

//                        new UserController(new Login());
//                        registerView.dispose();
                    } else {
                        loginView.showMessage("PASSWORD SALAH. SILAHKAN COBA LAGI.");
                    }
                } else {
                    loginView.showMessage("EMAIL TIDAK DITEMUKAN. SILAHKAN COBA LAGI ATAU DAFTAR TERLEBIH DAHULU.");
                }
            }
        } catch (Exception e) {
        }
    }

    public void register() {
        String username = registerView.getUsername().getText();
        String email = registerView.getEmail().getText();
        String password = new String(registerView.getPassword().getPassword());
        String confPassword = new String(registerView.getConfPassword().getPassword());

        try {
            if (username.equals("") || email.equals("") || password.equals("") || confPassword.equals("")) {
                registerView.showMessage("USERNAME / EMAIL / PASSWORD / CONFIRM PASSWORD HARUS DI ISI");
            } else if (password.length() < 6 || confPassword.length() < 6) {
                registerView.showMessage("PASSWORD / CONFIRM PASSWORD MINIMAL 6 KATA");
            } else if (!confPassword.equals(password)) {
                registerView.showMessage("PASSWORD / CONFIRM PASSWORD HARUS SAMA");
            } else {
                conn = koneksi.koneksi();
                pst = conn.prepareStatement("SELECT email FROM user WHERE email = '"
                        + email + "'");
                rst = pst.executeQuery();

                if (!rst.next()) {
                    pst = conn.prepareStatement("INSERT INTO user (username, email, password) VALUES ('"
                            + username + "','"
                            + email + "','"
                            + password + "');");
                    pst.execute();
                    new User(username, email, password);

                    registerView.showMessage("PENDAFTARAN BERHASIL");
                    new UserController(new Login());
                    registerView.dispose();

                } else {
                    registerView.showMessage("EMAIL SUDAH TERDAFTAR. SILAHKAN LOGIN ATAU GUNAKAN EMAIL LAIN.");
                }
            }
        } catch (Exception e) {
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
