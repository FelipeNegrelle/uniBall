package com.felipe.uniball;

import com.felipe.uniball.view.Form;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Form::new);
//        SwingUtilities.invokeLater(Menu::new);
//        SwingUtilities.invokeLater(com.felipe.uniball.view.Players::new);
    }
}