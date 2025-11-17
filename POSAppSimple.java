package POS;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// MainApp
public class POSAppSimple {
    // Window
    private final JFrame frame = new JFrame("Simple POS");

    // Model
    private final Cart cart = new Cart();
    private final CartTableModel tableModel = new CartTableModel(cart);

    // Totals
    private final JLabel lblSubtotal = new JLabel("Subtotal: ₱0.00");
    private final JLabel lblTax = new JLabel("Tax: ₱0.00");
    private final JLabel lblTotal = new JLabel("Total: ₱0.00");

    // Tax
    private final double TAX_RATE = 0.12; // 12%

    // Catalog
    private final List<Product> catalog = new ArrayList<>();

    public POSAppSimple() {
        createSampleCatalog();
        initUI();
    }

    // Samples
    private void createSampleCatalog() {
        catalog.add(new FoodItem("Burger", 5.99));
        catalog.add(new FoodItem("Fries", 2.49));
        catalog.add(new FoodItem("Hotdog", 3.25));
        catalog.add(new DrinkItem("Cola", 1.50));
        catalog.add(new DrinkItem("Coffee", 2.25));
        catalog.add(new DrinkItem("Water", 1.00));
    }

    // UI
    private void initUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Products
        JPanel productPanel = new JPanel(new GridLayout(0, 3, 6, 6));
        for (Product p : catalog) {
            // HTML
            JButton b = new JButton("<html>" + p.getName() + "<br/>₱" + String.format("%.2f", p.getPrice()) + "</html>");
            b.setFocusable(false);
            b.addActionListener(e -> {
                cart.addProduct(p);
                refreshCartView();
            });
            productPanel.add(b);
        }

        JPanel left = new JPanel(new BorderLayout());
        left.add(new JLabel("Products", SwingConstants.CENTER), BorderLayout.NORTH);
        left.add(productPanel, BorderLayout.CENTER);

        // CartTable
        JTable table = new JTable(tableModel);
        JScrollPane tablePane = new JScrollPane(table);
        JPanel right = new JPanel(new BorderLayout());
        right.add(new JLabel("Cart", SwingConstants.CENTER), BorderLayout.NORTH);
        right.add(tablePane, BorderLayout.CENTER);

        // Bottom
        JPanel totals = new JPanel(new GridLayout(3, 1));
        totals.add(lblSubtotal);
        totals.add(lblTax);
        totals.add(lblTotal);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCheckout = new JButton("Checkout");
        JButton btnClear = new JButton("Clear Cart");

        btnCheckout.addActionListener(e -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Cart is empty.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            CheckoutDialog dlg = new CheckoutDialog(frame, cart, TAX_RATE);
            dlg.setVisible(true);
        });

        btnClear.addActionListener(e -> {
            cart.clear();
            refreshCartView();
        });

        controls.add(btnClear);
        controls.add(btnCheckout);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(totals, BorderLayout.WEST);
        bottom.add(controls, BorderLayout.EAST);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        split.setResizeWeight(0.5);
        frame.add(split, BorderLayout.CENTER);
        frame.add(bottom, BorderLayout.SOUTH);

        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
    }

    // Refresh
    private void refreshCartView() {
        tableModel.fireTableDataChanged();
        double subtotal = cart.getSubtotal();
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;
        lblSubtotal.setText(String.format("Subtotal: ₱%.2f", subtotal));
        lblTax.setText(String.format("Tax: ₱%.2f", tax));
        lblTotal.setText(String.format("Total: ₱%.2f", total));
    }

    // Show
    public void show() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public static void main(String[] args) {
        new POSAppSimple().show();
    }
}
