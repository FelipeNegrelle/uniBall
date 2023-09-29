package com.felipe.uniball.view;

import com.felipe.uniball.Constants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.felipe.uniball.controller.Util;
import com.felipe.uniball.models.Player;

import java.awt.*;
import java.util.List;

import static com.felipe.uniball.Constants.NEW_GAME;

public class NewGame extends JFrame {
    private static final DefaultTableModel model = new DefaultTableModel(new Object[]{"Nome", "Número", "Posição"}, 0);

    public NewGame() {
        super(NEW_GAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new MigLayout("fill, insets 10", "[grow]", "[grow]"));
        getContentPane().setBackground(new Color(0x096B06));
        setPreferredSize(new Dimension(1920, 1080));

        JPanel panel = new JPanel(new MigLayout("fill, insets 20", "[30%][40%][30%]", "[grow]"));
        panel.setPreferredSize(new Dimension(1600, 900));

        JLabel titleLabel = new JLabel(NEW_GAME, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Sans", java.awt.Font.BOLD, 40));
        titleLabel.setForeground(Color.white);
        add(titleLabel, "align center, wrap");

        JTable leftTable = new JTable(model);
        leftTable.setRowHeight(25);
        leftTable.setDefaultEditor(Object.class, null);
        leftTable.setFont(new Font("Sans", Font.PLAIN, 18));
        leftTable.getTableHeader().setReorderingAllowed(false);
        leftTable.getTableHeader().setResizingAllowed(false);
        leftTable.getTableHeader().setFont(new Font("Sans", Font.BOLD, 15));
        JScrollPane leftScrollPane = new JScrollPane(leftTable);

        JButton leftButton = new JButton(Constants.ADD_PLAYER);
        leftButton.setFont(new Font("Sans", Font.BOLD, 30));
        leftButton.setForeground(Color.WHITE);
        leftButton.setPreferredSize(new Dimension(400, 100));
        leftButton.setBackground(new Color(0x096B06));
        leftButton.addActionListener(e -> {
//            new Components.RegistrationDialog(this, false).setVisible(true);
            updatePlayers();
        });

        JPanel leftPanel = new JPanel(new MigLayout("fill", "[grow]", "[grow]"));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(900, 800));
        leftPanel.add(leftScrollPane, "grow");
//        leftPanel.add(leftButton, "align center, wrap");

        JPanel middlePanel = new JPanel(new MigLayout("fill", "[center]", "[center]"));
        middlePanel.setBackground(Color.WHITE);

        JButton menuButton = new JButton("Voltar");
        menuButton.setFont(new Font("Sans", Font.BOLD, 30));
        menuButton.setForeground(Color.WHITE);
        menuButton.setPreferredSize(new Dimension(400, 100));
        menuButton.setBackground(new Color(0xBE0606));
        menuButton.addActionListener(e -> {
            dispose();
            new Menu();
        });
        middlePanel.add(menuButton, "wrap");

        JButton startButton = new JButton(NEW_GAME);
        startButton.setFont(new Font("Sans", Font.BOLD, 30));
        startButton.setForeground(Color.WHITE);
        startButton.setPreferredSize(new Dimension(400, 100));
        startButton.setBackground(new Color(0x096B06));
        startButton.addActionListener(e -> {
            updatePlayers();
//            dispose();
//            new Game();
        });
        middlePanel.add(startButton, "align center");

        JTable rightTable = new JTable(model);
        //        leftTable.getSelectionModel().addListSelectionListener(e -> table.repaint());
        rightTable.setRowHeight(25);
        rightTable.setFont(new Font("Sans", Font.PLAIN, 18));
        rightTable.setDefaultEditor(Object.class, null);
        rightTable.getTableHeader().setReorderingAllowed(false);
        rightTable.getTableHeader().setResizingAllowed(false);
        rightTable.getTableHeader().setFont(new Font("Sans", Font.BOLD, 15));
        JScrollPane rightScrollPane = new JScrollPane(rightTable);

        JPanel rightPanel = new JPanel(new MigLayout("fill", "[grow]", "[grow]"));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(rightScrollPane, "grow");

        panel.add(leftPanel, "grow");
        panel.add(middlePanel, "center");
        panel.add(rightPanel, "grow");

        add(panel, "align center");
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private static void updatePlayers() {
        model.setRowCount(0);

        List<Player> playerList = Util.getPlayers();
        for (Player player : playerList) {
            model.addRow(new Object[]{
                    player.getName(),
                    player.getNumber(),
                    player.getPosition(),
            });
        }
    }
}