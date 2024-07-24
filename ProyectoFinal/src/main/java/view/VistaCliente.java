package view;

import controller.Controlador;
import models.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaCliente extends JFrame {
    private Controlador controlador;
    private DefaultListModel<String> listModel;
    private DefaultListModel<String> estadoListModel;

    public VistaCliente(Controlador controlador) {
        this.controlador = controlador;
        this.setTitle("Tienda - Cliente");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel tipoLabel = new JLabel("Material:");
        JComboBox<String> tipoComboBox = new JComboBox<>(new String[]{"madera", "hierro"});
        JLabel nombreLabel = new JLabel("Producto:");
        JComboBox<String> nombreComboBox = new JComboBox<>(new String[]{"silla", "mesa", "estanteria", "armario"});
        JLabel cantidadLabel = new JLabel("Cantidad:");
        JSpinner cantidadSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

        JButton pedirButton = new JButton("Comprar");
        pedirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) tipoComboBox.getSelectedItem();
                String nombre = (String) nombreComboBox.getSelectedItem();
                int cantidad = (int) cantidadSpinner.getValue();
                controlador.realizarPedido(tipo, nombre, cantidad);
            }
        });

        inputPanel.add(nombreLabel);
        inputPanel.add(nombreComboBox);
        inputPanel.add(tipoLabel);
        inputPanel.add(tipoComboBox);
        inputPanel.add(cantidadLabel);
        inputPanel.add(cantidadSpinner);
        inputPanel.add(pedirButton);

        panel.add(inputPanel);

        listModel = new DefaultListModel<>();
        JList<String> pedidosList = new JList<>(listModel);
        JScrollPane pedidosScrollPane = new JScrollPane(pedidosList);

        estadoListModel = new DefaultListModel<>();
        JList<String> estadoList = new JList<>(estadoListModel);
        JScrollPane estadoScrollPane = new JScrollPane(estadoList);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pedidosScrollPane, estadoScrollPane);
        splitPane.setDividerLocation(200);

        this.add(panel, BorderLayout.NORTH);
        this.add(splitPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void agregarPedido(Producto producto) {
        String pedidoInfo = producto.getNombre() + " (" + producto.getMaterial() + "): " + producto.getCantidad();
        listModel.addElement(pedidoInfo);
    }

    public void notificarPedido(Producto producto, boolean aprobado) {
        String mensaje;
        if (aprobado) {
            mensaje = "El pedido del producto " + producto.getNombre() + " (" + producto.getMaterial() + "): " + producto.getCantidad() + " ha sido aprobado y está en fabricación.";
        } else {
            mensaje = producto != null ?
                    "El pedido del producto " + producto.getNombre() + " (" + producto.getMaterial() + "): " + producto.getCantidad() + " ha sido rechazado o no hay suficiente stock." :
                    "No hay suficiente stock para el pedido solicitado.";
        }
        estadoListModel.addElement(mensaje);
    }

    public void actualizarEstadoPedidos(String estado) {
        estadoListModel.addElement(estado);
    }
}