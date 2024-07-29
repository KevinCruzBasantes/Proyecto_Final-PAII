package view;

import controller.Controlador;
import util.ProductoDTO;
import util.ProductoAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaCliente extends JFrame {

    private Controlador controlador;
    private JPanel panelProductos;
    private JButton btnRealizarPedido;
    private JTextArea estadoPedidosArea;
    private ProductoAPI productoAPI;
    private JTextField notificacionTextField;

    public VistaCliente(Controlador controlador) {
        this.controlador = controlador;
        this.productoAPI = new ProductoAPI();
        inicializarComponentes();
        cargarProductos();
    }

    private void inicializarComponentes() {
        setTitle("Cliente - Realizar Pedido");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 3, 10, 10));
        add(panelProductos, BorderLayout.CENTER);

        btnRealizarPedido = new JButton("Realizar Pedido");
        btnRealizarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.realizarPedido();
                actualizarEstadoPedidos("Pedido realizado.");
            }
        });

        estadoPedidosArea = new JTextArea(10, 30);
        estadoPedidosArea.setEditable(false);

        notificacionTextField = new JTextField();
        notificacionTextField.setEditable(false);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BorderLayout());
        panelInferior.add(btnRealizarPedido, BorderLayout.NORTH);
        panelInferior.add(new JScrollPane(estadoPedidosArea), BorderLayout.CENTER);
        panelInferior.add(notificacionTextField, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);
    }

    public void cargarProductos() {  // Cambiado a public
        ProductoDTO[] productos = productoAPI.obtenerProductos();
        for (ProductoDTO producto : productos) {
            agregarProductoAlPanel(producto);
        }
    }

    private void agregarProductoAlPanel(ProductoDTO producto) {
        JPanel panelProducto = new JPanel();
        panelProducto.setLayout(new BoxLayout(panelProducto, BoxLayout.Y_AXIS));

        JLabel lblProducto = new JLabel(producto.getNombre());
        lblProducto.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnAgregar = new JButton("Añadir al carrito");
        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField txtCantidad = new JTextField();
        txtCantidad.setMaximumSize(new Dimension(50, 20));
        txtCantidad.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelProducto.add(lblProducto);
        panelProducto.add(Box.createRigidArea(new Dimension(0, 10)));
        panelProducto.add(btnAgregar);
        panelProducto.add(Box.createRigidArea(new Dimension(0, 10)));
        panelProducto.add(new JLabel("Cantidad:"));
        panelProducto.add(txtCantidad);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad;
                try {
                    cantidad = Integer.parseInt(txtCantidad.getText());
                    if (cantidad <= 0) {
                        JOptionPane.showMessageDialog(VistaCliente.this, "La cantidad debe ser positiva.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(VistaCliente.this, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                controlador.agregarProducto(producto.getMaterial(), producto.getNombre(), cantidad);
                String mensaje = "Producto " + producto.getNombre() + " añadido al carrito con cantidad " + cantidad + ".";
                actualizarEstadoPedidos(mensaje);
            }
        });

        panelProductos.add(panelProducto);
        panelProductos.revalidate();
        panelProductos.repaint();
    }



    public void actualizarEstadoPedidos(String mensaje) {
        estadoPedidosArea.append(mensaje + "\n");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
}
