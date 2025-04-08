/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.assignment;

/**
 *
 * @author PC
 */
public interface ClientLogin {
    boolean authenticate(String username, String password);
    void showError(String message);
    void onLoginSuccess();
}
