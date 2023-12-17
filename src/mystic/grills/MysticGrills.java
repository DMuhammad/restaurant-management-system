/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mystic.grills;

import controller.UserController;
import view.Login;

/**
 *
 * @author Dzikri
 */
public class MysticGrills {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Login loginView = new Login();
        
        UserController userController = new UserController(loginView);
    }
    
}
