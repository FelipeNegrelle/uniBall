package com.felipe.uniball.view;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;

import static com.felipe.uniball.Constants.*;
import static com.felipe.uniball.Constants.GREEN;

public class Menu extends JFrame {
    public Menu() {
        super("Menu Inicial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(GREEN);

        JPanel mainPanel = new JPanel(new MigLayout("gapx 50, gapy 50, align center", "[fill]"));
        mainPanel.setBackground(GREEN);

        JPanel titlePanel = new JPanel(new MigLayout("fill", "[fill]"));
        titlePanel.setBackground(GREEN);

        JLabel titleLabel = new JLabel(UNIBALL);
        titleLabel.setFont(new Font("Sans", Font.BOLD, 80));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, "wrap, align center");

        JButton players = new JButton("Jogadores");
        players.addActionListener(e -> {
            dispose();
            new Players();
        });

        JButton match = new JButton(NEW_GAME);
        match.addActionListener(e -> {
            dispose();
            new NewGame();
        });

        JButton ranking = new JButton("Ranking");
        ranking.addActionListener(e -> {
            dispose();
            new Ranking();
        });

        JButton matchesHistory = new JButton("Histórico de partidas");
        matchesHistory.addActionListener(e -> {
            dispose();
            new MatchesHistory();
        });

        JButton sair = new JButton("Sair");
        sair.addActionListener(e -> {
            new Form();
            dispose();
        });

        players.setPreferredSize(new Dimension(400, 150));
        players.setBackground(Color.white);
        players.setForeground(Color.black);
        players.setFont(new Font("Tahoma", Font.BOLD, 50));

        match.setPreferredSize(new Dimension(400, 150));
        match.setBackground(Color.white);
        match.setForeground(Color.black);
        match.setFont(new Font("Tahoma", Font.BOLD, 50));

        ranking.setPreferredSize(new Dimension(400, 150));
        ranking.setBackground(Color.white);
        ranking.setForeground(Color.black);
        ranking.setFont(new Font("Tahoma", Font.BOLD, 50));

        matchesHistory.setPreferredSize(new Dimension(400, 150));
        matchesHistory.setBackground(Color.white);
        matchesHistory.setForeground(Color.black);
        matchesHistory.setFont(new Font("Tahoma", Font.BOLD, 50));

        sair.setPreferredSize(new Dimension(400, 150));
        sair.setBackground(RED);
        sair.setForeground(Color.WHITE);
        sair.setFont(new Font("Tahoma", Font.BOLD, 50));

        mainPanel.add(titlePanel, "wrap, align center");
        mainPanel.add(players);
        mainPanel.add(match);
        mainPanel.add(ranking, "wrap");
        mainPanel.add(matchesHistory);
        mainPanel.add(sair, "wrap");
        add(mainPanel);

        pack();
        setLocationRelativeTo(this);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}