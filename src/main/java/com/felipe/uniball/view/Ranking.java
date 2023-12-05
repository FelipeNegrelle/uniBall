package com.felipe.uniball.view;

import com.felipe.uniball.Constants;
import com.felipe.uniball.controller.Util;
import com.felipe.uniball.models.Player;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Ranking extends JFrame {
    private static DefaultTableModel model;

    public Ranking() {
        super("Ranking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[fill]"));

        JPanel panel = new JPanel(new MigLayout("wrap 5", "[grow]", "[][grow][]"));
        panel.setBackground(Constants.GREEN);

        JLabel titleLabel = new JLabel("Ranking");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, "span, center, gapbottom 15");

        model = new DefaultTableModel(new Object[]{"Classificação", "Nome", "Gols", "Gol mais bonito", "Melhor jogador"}, 0);

        JTable table = new JTable(model);
        table.setFont(new Font("Sans", Font.PLAIN, 20));
        table.setSelectionBackground(Constants.GREEN);
        table.setSelectionForeground(Color.WHITE);

        JComboBox<String> filterComboBox = new JComboBox<>(new String[]{"Gols", "Melhor jogador", "Gol mais bonito"});
        filterComboBox.setFont(new Font("Sans", Font.PLAIN, 35));
        filterComboBox.setPreferredSize(new Dimension(300, 50));
        filterComboBox.setBackground(Color.WHITE);
        filterComboBox.setForeground(Color.BLACK);

        JButton filterButton = new JButton("Filtrar");
        filterButton.setBackground(Color.WHITE);
        filterButton.setForeground(Color.BLACK);
        filterButton.setFont(new Font("Tahoma", Font.BOLD, 50));
        filterButton.addActionListener(e -> {
            if (filterComboBox.getSelectedItem() == "Gols") {
                updateRankingTable("score");
            } else if (filterComboBox.getSelectedItem() == "Melhor jogador") {
                updateRankingTable("best_player");
            } else if (filterComboBox.getSelectedItem() == "Gol mais bonito") {
                updateRankingTable("beautiful_score");
            }
        });

        JButton returnButton = new JButton("Voltar");
        returnButton.addActionListener(e -> {
            dispose();
            new Menu();
        });
        returnButton.setBackground(Constants.RED);
        returnButton.setForeground(Color.WHITE);
        returnButton.setFont(new Font("Tahoma", Font.BOLD, 50));

        table.getSelectionModel().addListSelectionListener(e -> table.repaint());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(30);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(new Font("Sans", Font.BOLD, 20));

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, "span, grow");
        panel.add(returnButton, "align left");
        panel.add(filterComboBox, "align right, grow");
        panel.add(filterButton, "align right");

        updateRankingTable("score");

        add(panel, "align center, grow");
        pack();
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private static void updateRankingTable(String campo) {
        model.setRowCount(0);

        List<Player> playerList = Util.getPlayers(campo, "DESC");
        for (Player player : playerList) {
            model.addRow(new Object[]{
                    playerList.indexOf(player) + 1 + "º",
                    player.getName(),
                    player.getScore(),
                    player.getBeautifulScore(),
                    player.getBestPlayer()
            });
        }
    }
}
