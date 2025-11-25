# Simple POS (Swing)

Overview
- Simple Point-of-Sale desktop application implemented with Java Swing.
- Core classes: `Product` (abstract), `FoodItem` / `DrinkItem` (concrete), `CartItem`, `Cart`.

Files
- `POSAppSimple.java` — main application (UI, product buttons, table, totals).
- `Cart.java` — cart model; encapsulates `List<CartItem>` and provides add/remove/clear/subtotal.
- `CartItem.java` — pairs a `Product` with a `quantity` and provides helpers.
- `CartTableModel.java` — table model used by the cart `JTable`.
- `Product.java` — abstract product with `name` and `price`.
- `FoodItem.java`, `DrinkItem.java` — simple concrete product types.
- `CheckoutDialog.java` — modal dialog that displays a checkout summary.

Requirements
- Java SE (JDK) 8 or newer.

Build & Run (Windows PowerShell)
1. Open PowerShell and change to the project folder that contains the `POS` package folder:

```powershell
cd "c:\Users\geony\OneDrive\Documents\Computer Science\Class\Subjects\OOP\Code\Final Visual Studio Code\POS"
```

2. Compile the source files:

```powershell
javac POS\\*.java
```

3. Run the application:

```powershell
java POS.POSAppSimple
```

User Guide

Product Panel
- Click a product button to add that product to the cart. Repeated clicks increase the quantity of the existing cart item.

Cart Table
- Column `Item`: product name with a small icon.
- Column `Qty`: editable cell — type a positive integer and press Enter to update quantity. Entering `0` removes the item.
- Column `Price`: unit price.
- Column `Total`: line total (`price * quantity`).
- Column `Action` (`−`): a button that decrements quantity by 1; when quantity reaches 0 the item is removed.

Controls
- `Clear Cart`: removes all items from the cart.
- `Checkout`: opens the checkout dialog that displays a receipt-style list of items, subtotal, tax, and total.

Totals & Tax
- Subtotal, Tax, and Total are shown in the bottom-left tiles and update automatically.
- Tax rate used: 12% (TAX_RATE = 0.12).

Behavior & Edge Cases
- Product matching: the cart matches products by `name` (string equality). If you add two distinct products with the same name, they will be merged. For production use, consider adding unique product IDs.
- Input validation: quantity edits use `Integer.parseInt`; invalid inputs are ignored and the previous value is retained.
- Persistence: this demo does not persist cart contents or receipts.

Troubleshooting
- "Class not found" when running: ensure you are in the folder that contains the `POS` package and that compilation succeeded.
- UI does not update after an action: the app calls `refreshCartView()` after operations; if you modified code, ensure `fireTableDataChanged()` is called on the table model.

Notes
- The implementation focuses on clarity and instructional value. The cart and table model logic are kept separate to demonstrate good OOP practices.

Author
- POS project
