package view;
import controller.Controlador;
import models.ProgressObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Vista extends JFrame implements ProgressObserver {
    private Controlador controlador;
    private JComboBox<String> productoComboBox;
    private JProgressBar progressBar;
    private JLabel progressLabel;
    private JPanel mainPanel;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
        this.setTitle("Tienda");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());

        JPanel homePanel = new JPanel();
        JButton clienteButton = new JButton("Cliente");
        JButton administradorButton = new JButton("Administrador");

        homePanel.add(clienteButton);
        homePanel.add(administradorButton);

        mainPanel.add(homePanel, "home");

        JPanel clientePanel = new JPanel();
        clientePanel.setLayout(new FlowLayout());
        productoComboBox = new JComboBox<>(new String[]{"madera", "hierro"});
        JButton comprarButton = new JButton("Comprar");

        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoProducto = (String) productoComboBox.getSelectedItem();
                controlador.realizarPedido(tipoProducto, Vista.this);
            }
        });

        clientePanel.add(new JLabel("Seleccione producto:"));
        clientePanel.add(productoComboBox);
        clientePanel.add(comprarButton);

        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new BorderLayout());
        progressLabel = new JLabel("Progreso:");
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressPanel.add(progressLabel, BorderLayout.NORTH);
        progressPanel.add(progressBar, BorderLayout.CENTER);

        clientePanel.add(progressPanel);

        mainPanel.add(clientePanel, "cliente");

        administradorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Vista.this, "Administración de pedidos en curso...");
            }
        });

        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "cliente");
            }
        });

        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void updateProgress(String step, int progress) {
        SwingUtilities.invokeLater(() -> {
            progressLabel.setText("Progreso: " + step);
            progressBar.setValue(progress);
        });
    }
}