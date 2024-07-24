package view;

import controller.Controlador;
import models.Producto;
import models.ProgressObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class VistaAdministrador extends JFrame implements ProgressObserver {
    private Controlador controlador;
    private DefaultListModel<Producto> listModel;
    private JList<Producto> pedidosList;
    private Map<Producto, JProgressBar> progressBars;
    private Map<Producto, JComboBox<String>> approvalComboBoxes;
    private Map<Producto, JLabel> stockLabels;

    public VistaAdministrador(Controlador controlador) {
        this.controlador = controlador;
        this.setTitle("Tienda - Administrador");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        pedidosList = new JList<>(listModel);
        pedidosList.setVisibleRowCount(4);
        JScrollPane scrollPane = new JScrollPane(pedidosList);

        progressBars = new HashMap<>();
        approvalComboBoxes = new HashMap<>();
        stockLabels = new HashMap<>();

        JButton procesarButton = new JButton("Procesar");

        procesarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Map.Entry<Producto, JComboBox<String>> entry : approvalComboBoxes.entrySet()) {
                    Producto producto = entry.getKey();
                    boolean fabricar = "Sí".equals(entry.getValue().getSelectedItem());
                    controlador.aprobarPedido(producto, fabricar);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(scrollPane);
        panel.add(procesarButton);

        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void agregarPedido(Producto producto, int cantidad, int stock) {
        listModel.addElement(producto);
        JPanel productPanel = new JPanel(new BorderLayout());

        JLabel productLabel = new JLabel(producto.toString());
        productPanel.add(productLabel, BorderLayout.WEST);

        JComboBox<String> approvalComboBox = new JComboBox<>(new String[]{"No", "Sí"});
        productPanel.add(approvalComboBox, BorderLayout.CENTER);
        approvalComboBoxes.put(producto, approvalComboBox);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        productPanel.add(progressBar, BorderLayout.EAST);
        progressBars.put(producto, progressBar);

        JLabel stockLabel = new JLabel("Stock: " + stock);
        productPanel.add(stockLabel, BorderLayout.SOUTH);
        stockLabels.put(producto, stockLabel);

        ((JPanel) this.getContentPane().getComponent(0)).add(productPanel);
        this.revalidate();
    }

    @Override
    public void updateProgress(String step, int progress, Producto producto) {
        JProgressBar progressBar = progressBars.get(producto);
        if (progressBar != null) {
            progressBar.setValue(progress);
            progressBar.setString(step);
        }
    }

    public void actualizarStock(Producto producto, int nuevoStock) {
        JLabel stockLabel = stockLabels.get(producto);
        if (stockLabel != null) {
            stockLabel.setText("Stock: " + nuevoStock);
        }
    }

    public void resetProgress(Producto producto) {
        JProgressBar progressBar = progressBars.get(producto);
        if (progressBar != null) {
            progressBar.setValue(0);
            progressBar.setString("Fabricación no aprobada");
        }
    }
}
