package com.felipe.uniball.view;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;

public class Menu extends JFrame {
    public Menu() {
        super("Menu Inicial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new MigLayout("gapx 50, gapy 50, align center", "[fill]"));
        JPanel sairPanel = new JPanel(new MigLayout("fill", "[grow]"));
        JPanel titlePanel = new JPanel(new MigLayout("fill", "[fill]"));

        JLabel titleLabel = new JLabel("UNIBALL");
        titleLabel.setFont(new Font("Sans", Font.BOLD, 80));
        titleLabel.setForeground(Color.BLACK);
        titlePanel.add(titleLabel, "wrap, align center");

        JButton jogadores = new JButton("Jogadores");
        jogadores.addActionListener(e -> {
            dispose();
            new Players();
        });

        JButton partida = new JButton("Nova partida");
        JButton ranking = new JButton("Ranking");
        JButton sair = new JButton("Sair");

        jogadores.setPreferredSize(new Dimension(400, 150));
        jogadores.setBackground(new Color(0x096B06));
        jogadores.setForeground(Color.WHITE);
        jogadores.setFont(new Font("Tahoma", Font.BOLD, 50));

        partida.setPreferredSize(new Dimension(400, 150));
        partida.setBackground(new Color(0x096B06));
        partida.setForeground(Color.WHITE);
        partida.setFont(new Font("Tahoma", Font.BOLD, 50));

        ranking.setPreferredSize(new Dimension(400, 150));
        ranking.setBackground(new Color(0x096B06));
        ranking.setForeground(Color.WHITE);
        ranking.setFont(new Font("Tahoma", Font.BOLD, 50));

        sair.setPreferredSize(new Dimension(400, 150));
        sair.setBackground(new Color(0xBE0606));
        sair.setForeground(Color.WHITE);
        sair.setFont(new Font("Tahoma", Font.BOLD, 50));
        sair.addActionListener(e -> System.exit(0));

        mainPanel.add(titlePanel, "wrap, align center");
        mainPanel.add(jogadores);
        mainPanel.add(partida);
        mainPanel.add(ranking, "wrap");
        sairPanel.add(sair);
        mainPanel.add(sairPanel);
        add(mainPanel);

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}