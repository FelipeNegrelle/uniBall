package com.felipe.uniball.view;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;

import static com.felipe.uniball.Constants.NEW_GAME;
import static com.felipe.uniball.Constants.UNIBALL;

public class Menu extends JFrame {
    public Menu() {
        super("Menu Inicial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBackground(new Color(0x096B06));

        JPanel mainPanel = new JPanel(new MigLayout("gapx 50, gapy 50, align center", "[fill]"));
        mainPanel.setBackground(new Color(0x096B06));

        JPanel titlePanel = new JPanel(new MigLayout("fill", "[fill]"));
        titlePanel.setBackground(new Color(0x096B06));

        JLabel titleLabel = new JLabel(UNIBALL);
        titleLabel.setFont(new Font("Sans", Font.BOLD, 80));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, "wrap, align center");

        JButton jogadores = new JButton("Jogadores");
        jogadores.addActionListener(e -> {
            dispose();
            new Players();
        });

        JButton partida = new JButton(NEW_GAME);
        partida.addActionListener(e -> {
            dispose();
            new NewGame();
        });
        JButton ranking = new JButton("Ranking");
        JButton sair = new JButton("Sair");

        jogadores.setPreferredSize(new Dimension(400, 150));
        jogadores.setBackground(Color.white);
        jogadores.setForeground(Color.black);
        jogadores.setFont(new Font("Tahoma", Font.BOLD, 50));

        partida.setPreferredSize(new Dimension(400, 150));
        partida.setBackground(Color.white);
        partida.setForeground(Color.black);
        partida.setFont(new Font("Tahoma", Font.BOLD, 50));

        ranking.setPreferredSize(new Dimension(400, 150));
        ranking.setBackground(Color.white);
        ranking.setForeground(Color.black);
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
        mainPanel.add(sair, "wrap");
//        sairPanel.add(sair);
//        mainPanel.add(sairPanel);
        add(mainPanel);

        pack();
        setLocationRelativeTo(this);
        setVisible(true);
    }
}