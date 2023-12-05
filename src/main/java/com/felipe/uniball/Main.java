package com.felipe.uniball;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        SwingUtilities.invokeLater(com.felipe.uniball.view.Form::new);
    }
}