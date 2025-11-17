package POS;

import javax.swing.table.AbstractTableModel;
import java.util.List;

// TableModel
public class CartTableModel extends AbstractTableModel {
    private final Cart cart;
    private final String[] columns = {"Item", "Qty", "Price", "Total"};

    public CartTableModel(Cart cart) {
        this.cart = cart;
    }

    @Override
    public int getRowCount() {
        return cart.getItems().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    // Display
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<CartItem> items = cart.getItems();
        CartItem ci = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ci.getProduct().getName();
            case 1:
                return ci.getQuantity();
            case 2:
                return String.format("₱%.2f", ci.getProduct().getPrice());
            case 3:
                return String.format("₱%.2f", ci.getTotalPrice());
            default:
                return "";
        }
    }
}
