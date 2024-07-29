package view;

import controller.Controlador;
import models.ProgressObserver;
import util.ProductoDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class VistaAdministrador extends JFrame implements ProgressObserver {
    private Controlador controlador;
    private DefaultListModel<ProductoDTO> listModel;
    private JList<ProductoDTO> pedidosList;
    private Map<ProductoDTO, JProgressBar> progressBars;
    private Map<ProductoDTO, JComboBox<String>> approvalComboBoxes;
    private Map<ProductoDTO, JLabel> stockLabels;
    private Map<ProductoDTO, Thread> processThreads;
    private JTextField nombreTextField;
    private JTextField cantidadTextField;
    private JComboBox<String> tipoComboBox;
    private JPanel ordersPanel;
    private VistaCliente vistaCliente;

    public VistaAdministrador(Controlador controlador) {
        this.controlador = controlador;
        this.setTitle("Tienda - Administrador");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        pedidosList = new JList<>(listModel);
        pedidosList.setVisibleRowCount(4);
        JScrollPane scrollPane = new JScrollPane(pedidosList);

        progressBars = new HashMap<>();
        approvalComboBoxes = new HashMap<>();
        stockLabels = new HashMap<>();
        processThreads = new HashMap<>();

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(0, 2));

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreTextField = new JTextField();
        southPanel.add(nombreLabel);
        southPanel.add(nombreTextField);

        JLabel tipoLabel = new JLabel("Tipo:");
        tipoComboBox = new JComboBox<>(new String[]{"Madera", "Hierro"});
        southPanel.add(tipoLabel);
        southPanel.add(tipoComboBox);

        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadTextField = new JTextField();
        southPanel.add(cantidadLabel);
        southPanel.add(cantidadTextField);

        JButton agregarProductoButton = new JButton("Agregar Producto");
        JButton eliminarProductoButton = new JButton("Eliminar Producto");
        JButton realizarProcesosButton = new JButton("Realizar Procesos");

        southPanel.add(agregarProductoButton);
        southPanel.add(eliminarProductoButton);
        southPanel.add(new JLabel()); // Espacio vacío para alinear botones
        southPanel.add(realizarProcesosButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        agregarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearProducto();
            }
        });

        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto(pedidosList.getSelectedValue());
            }
        });

        realizarProcesosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarProcesos();
            }
        });

        this.add(panel, BorderLayout.NORTH);

        ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));

        JScrollPane ordersScrollPane = new JScrollPane(ordersPanel);
        this.add(ordersScrollPane, BorderLayout.CENTER);

        cargarProductosDesdeCarrito();
        this.setVisible(true);
    }

    private void actualizarOrdersPanel() {
        ordersPanel.removeAll();
        for (int i = 0; i < listModel.size(); i++) {
            ProductoDTO producto = listModel.get(i);
            JPanel orderPanel = new JPanel();
            orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
            JLabel nameLabel = new JLabel("Producto: " + producto.getNombre());
            JProgressBar progressBar = progressBars.get(producto);
            JLabel stockLabel = stockLabels.get(producto);
            JComboBox<String> approvalComboBox = approvalComboBoxes.get(producto);
            orderPanel.add(nameLabel);
            orderPanel.add(progressBar);
            orderPanel.add(stockLabel);
            orderPanel.add(approvalComboBox);
            ordersPanel.add(orderPanel);
        }
        ordersPanel.revalidate();
        ordersPanel.repaint();
    }

    public void cargarProductosDesdeCarrito() {
        Map<String, Integer> carrito = controlador.obtenerProductosCarrito();
        for (Map.Entry<String, Integer> entry : carrito.entrySet()) {
            String nombreProducto = entry.getKey();
            int cantidad = entry.getValue();
            ProductoDTO producto = new ProductoDTO(nombreProducto, cantidad);
            listModel.addElement(producto);
            JProgressBar progressBar = new JProgressBar();
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            progressBars.put(producto, progressBar);
            JLabel stockLabel = new JLabel("Stock: " + cantidad);
            stockLabels.put(producto, stockLabel);
            JComboBox<String> approvalComboBox = new JComboBox<>(new String[]{"Pendiente", "Aprobado", "Rechazado"});

            final ProductoDTO finalProducto = producto;
            approvalComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedStatus = (String) approvalComboBox.getSelectedItem();
                    if ("Rechazado".equals(selectedStatus)) {
                        notificarRechazo(finalProducto);
                        detenerProceso(finalProducto);
                    } else {
                        detenerProceso(finalProducto);
                    }
                }
            });
            approvalComboBoxes.put(producto, approvalComboBox);
        }
        actualizarOrdersPanel();
    }

    public void crearProducto() {
        String nombre = nombreTextField.getText();
        String tipo = (String) tipoComboBox.getSelectedItem();
        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ProductoDTO producto = new ProductoDTO(null, nombre, tipo, 0, cantidad); // Crear el producto DTO
        controlador.agregarProducto(producto);
    }

    public void eliminarProducto(ProductoDTO producto) {
        if (producto != null) {
            listModel.removeElement(producto);
            progressBars.remove(producto);
            stockLabels.remove(producto);
            approvalComboBoxes.remove(producto);
            detenerProceso(producto);
            controlador.eliminarProducto(producto.getId());
        }
    }

    public void iniciarProcesos() {
        for (int i = 0; i < listModel.size(); i++) {
            ProductoDTO producto = listModel.get(i);
            JComboBox<String> approvalComboBox = approvalComboBoxes.get(producto);
            if (approvalComboBox != null && "Aprobado".equals(approvalComboBox.getSelectedItem())) {
                iniciarProceso(producto);
            }
        }
    }

    private void iniciarProceso(ProductoDTO producto) {
        JProgressBar progressBar = progressBars.get(producto);
        if (progressBar != null) {
            detenerProceso(producto); // Detener cualquier proceso en ejecución previo
            Thread processThread = new Thread(new Proceso(producto, progressBar));
            processThreads.put(producto, processThread);
            processThread.start();
        }
    }

    private void detenerProceso(ProductoDTO producto) {
        Thread processThread = processThreads.get(producto);
        if (processThread != null && processThread.isAlive()) {
            processThread.interrupt();
            processThreads.remove(producto);
        }
    }

    private void notificarRechazo(ProductoDTO producto) {
        if (vistaCliente != null) {
            vistaCliente.mostrarMensaje("El producto '" + producto.getNombre() + "' ha sido rechazado.");
        } else {

            JOptionPane.showMessageDialog(this, "El producto '" + producto.getNombre() + "' ha sido rechazado por el administrador.", "Producto Rechazado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setVistaCliente(VistaCliente vistaCliente) {
        this.vistaCliente = vistaCliente;
    }

    @Override
    public void updateProgress(ProductoDTO producto, int progress) {
        JProgressBar progressBar = progressBars.get(producto);
        if (progressBar != null) {
            progressBar.setValue(progress);
        }
    }

    @Override
    public void updateStock(ProductoDTO producto, int stock) {
        JLabel stockLabel = stockLabels.get(producto);
        if (stockLabel != null) {
            stockLabel.setText("Stock: " + stock);
        }
    }

    @Override
    public void updateProgress(String step, int progress, ProductoDTO producto) {

    }

    private class Proceso implements Runnable {
        private ProductoDTO producto;
        private JProgressBar progressBar;

        public Proceso(ProductoDTO producto, JProgressBar progressBar) {
            this.producto = producto;
            this.progressBar = progressBar;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i <= 100; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        return; // Si el hilo es interrumpido, salir
                    }
                    int finalI = i;
                    SwingUtilities.invokeLater(() -> progressBar.setValue(finalI));
                    Thread.sleep(50); // Simular tiempo de procesamiento
                }
                //  actualizar stock
            } catch (InterruptedException e) {
                // Maneja la interrupción del hilo
            }
        }
    }

}