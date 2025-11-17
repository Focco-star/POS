package POS;

import javax.swing.*;
import java.awt.*;

// Checkout
public class CheckoutDialog extends JDialog {
    private final Cart cart;
    private final double taxRate;

    public CheckoutDialog(Frame owner, Cart cart, double taxRate) {
        super(owner, "Checkout", true);
        this.cart = cart;
        this.taxRate = taxRate;
        initUI();
        setLocationRelativeTo(owner);
    }

    private void initUI() {
        setLayout(new BorderLayout());
        JTextArea area = new JTextArea();
        area.setEditable(false);

        // Receipt
        StringBuilder sb = new StringBuilder();
        sb.append("Receipt:\n\n");
        for (CartItem ci : cart.getItems()) {
            sb.append(ci.getProduct().getName())
              .append(" x")
              .append(ci.getQuantity())
              .append(" = ")
              .append(String.format("₱%.2f", ci.getTotalPrice()))
              .append('\n');
        }

        double subtotal = cart.getSubtotal();
        double tax = subtotal * taxRate;
        double total = subtotal + tax;

        sb.append("\nSubtotal: ").append(String.format("₱%.2f", subtotal)).append('\n');
        sb.append("Tax: ").append(String.format("₱%.2f", tax)).append('\n');
        sb.append("Total: ").append(String.format("₱%.2f", total)).append('\n');

        area.setText(sb.toString());
        add(new JScrollPane(area), BorderLayout.CENTER);

        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton close = new JButton("Close");
        close.addActionListener(e -> dispose());
        south.add(close);
        add(south, BorderLayout.SOUTH);

        setSize(360, 320);
    }
}
