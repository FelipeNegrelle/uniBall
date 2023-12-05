package com.felipe.uniball.view;

import com.felipe.uniball.Constants;
import com.felipe.uniball.controller.Util;
import com.felipe.uniball.models.Match;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static com.felipe.uniball.Constants.GREEN;
import static com.felipe.uniball.Constants.RED;

public class MatchesHistory extends JFrame {
    private static DefaultTableModel model;

    public MatchesHistory() {
        super(Constants.MATCHES_HISTORY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));

        JPanel panel = new JPanel(new MigLayout("align center, wrap 4", "[grow]", "[][grow][]"));
        panel.setBackground(GREEN);

        JLabel titleLabel = new JLabel(Constants.MATCHES_HISTORY);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, "span, center, gapbottom 15");

        model = new DefaultTableModel(new Object[]{"Código", "Data/Hora", "Gols time A", "Gols time B", "Vencedor", "Melhor Jogador", "Gol mais bonito"}, 0);

        JTable table = new JTable(model);
        table.setFont(new Font("Sans", Font.PLAIN, 20));
        table.setSelectionBackground(GREEN);
        table.setSelectionForeground(Color.WHITE);

        JButton returnButton = new JButton(Constants.BACK);
        returnButton.addActionListener(e -> {
            dispose();
            new Menu();
        });
        returnButton.setBackground(RED);
        returnButton.setForeground(Color.WHITE);
        returnButton.setFont(new Font("Tahoma", Font.BOLD, 50));

        updatePlayerTable();

        table.getSelectionModel().addListSelectionListener(e -> table.repaint());
        table.setAutoCreateRowSorter(true);
        table.setRowHeight(30);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(new Font("Sans", Font.BOLD, 20));

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, "span, align center, grow");
        panel.add(returnButton, "split 4, align left");
        add(panel, "align center, grow");
        pack();
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private static void updatePlayerTable() {
        model.setRowCount(0);

        List<Match> matchList = Util.getMatches();
        for (Match match : matchList) {
            model.addRow(new Object[]{
                    match.getId(),
                    match.getDateTimeMatch(),
                    match.getScoreA(),
                    match.getScoreB(),
                    match.getWinner().equals("E") ? "Empate" : "Time " + match.getWinner(),
                    match.getBestPlayer(),
                    match.getPlayerBeautifulScore()
            });
        }
    }
}