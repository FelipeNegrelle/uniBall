package com.felipe.uniball;

import com.felipe.uniball.view.Components;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        SwingUtilities.invokeLater(com.felipe.uniball.view.Form::new);
//        SwingUtilities.invokeLater(com.felipe.uniball.view.Menu::new);
//        SwingUtilities.invokeLater(com.felipe.uniball.view.NewGame::new);
//        SwingUtilities.invokeLater(com.felipe.uniball.view.Ranking::new);
    }
}