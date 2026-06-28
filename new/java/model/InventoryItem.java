package model;

import java.io.Serializable;

public class InventoryItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String itemName;
    private int quantity;
    private String expiryDate;

    public InventoryItem() {}

    public InventoryItem(int id, String itemName, int quantity, String expiryDate) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
}