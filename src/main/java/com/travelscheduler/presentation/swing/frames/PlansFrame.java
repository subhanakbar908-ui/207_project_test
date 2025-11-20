package com.travelscheduler.presentation.swing.frames;

import com.travelscheduler.domain.entities.Plan;
import com.travelscheduler.presentation.presenters.PlansPresenter;
import com.travelscheduler.presentation.views.PlansView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlansFrame extends JFrame implements PlansView {

    private final PlansPresenter presenter;
    private JTable plansTable;
    private DefaultTableModel tableModel;
    private JButton refreshButton;
    private JButton deleteButton;

    public PlansFrame(PlansPresenter presenter) {
        super("My Plans");
        this.presenter = presenter;
        this.presenter.setView(this);

        initUI();

        // load plans as soon as window opens
        presenter.loadPlans();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "City", "Radius (km)", "Interests", "Locations", "#Stops"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        plansTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(plansTable);

        refreshButton = new JButton("Refresh");
        deleteButton = new JButton("Delete Selected");

        refreshButton.addActionListener(e -> presenter.loadPlans());

        deleteButton.addActionListener(e -> {
            int row = plansTable.getSelectedRow();
            if (row == -1) {
                showError("Please select a plan to delete.");
                return;
            }
            String planId = (String) tableModel.getValueAt(row, 0);
            presenter.deletePlan(planId);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void showPlans(List<Plan> plans) {
        tableModel.setRowCount(0); // clear
        for (Plan plan : plans) {
            String interests = joinArray(plan.getInterests());
            String locations = joinArray(plan.getLocations());
            int stops = plan.getVisits() != null ? plan.getVisits().size() : 0;

            tableModel.addRow(new Object[]{
                    plan.getId(),
                    plan.getCity(),
                    plan.getRadius(),
                    interests,
                    locations,
                    stops
            });
        }
    }

    private String joinArray(String[] arr) {
        if (arr == null || arr.length == 0) return "";
        return Stream.of(arr).collect(Collectors.joining(", "));
    }

    @Override
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }
}