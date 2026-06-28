<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.InventoryItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HEP Staff - Manage Inventory</title>
</head>
<body>

    <h1>HEP Staff View</h1>
    <hr>

    <table border="1" width="100%" cellpadding="10">
        <tr>
            <td width="20%" valign="top">
                <h3>Food Bank System</h3>
                <ul>
                    <li><a href="FoodBankController?action=dashboard">Dashboard</a></li>
                    <li><strong>Manage Inventory</strong></li>
                    <li><a href="HepDistribution.html">Manage Distribution</a></li>
                    <li><a href="FoodBankController?action=logout">Logout</a></li>
                </ul>
            </td>
            
            <td width="80%" valign="top">
                <h2>Manage Inventory</h2>
                
                <fieldset>
                    <legend><h3> Add New Item </h3></legend>
                    <form action="FoodBankController" method="POST">
                        <input type="hidden" name="action" value="addItem">
                        <label>Item Name:</label> <input type="text" name="item_name" required> &nbsp;&nbsp;
                        <label>Quantity:</label> <input type="number" name="quantity" required> &nbsp;&nbsp;
                        <label>Expiry Date:</label> <input type="date" name="expiry_date" required> &nbsp;&nbsp;
                        <input type="submit" value="Save New Item">
                    </form>
                </fieldset>
                <br>

                <h3>Current Stock Inventory Ledger (Dynamic Java Database View)</h3>
                <table border="1" cellpadding="8" width="100%">
                    <thead>
                        <tr bgcolor="#f0f0f0">
                            <th>Item ID</th>
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Expiry Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        List<InventoryItem> itemsList = (List<InventoryItem>) request.getAttribute("itemsList");
                        if (itemsList != null && !itemsList.isEmpty()) {
                            for (InventoryItem item : itemsList) {
                    %>
                        <tr>
                            <td><%= item.getId() %></td>
                            <td><%= item.getItemName() %></td>
                            <td><%= item.getQuantity() %></td>
                            <td><%= item.getExpiryDate() %></td>
                            <td>
                                <button type="button" onclick="alert('Trigger Update Form workflow')">Edit</button> 
                                <a href="FoodBankController?action=deleteItem&id=<%= item.getId() %>" 
                                   onclick="return confirm('Are you sure you want to delete this record?')">
                                    <button type="button">Delete</button>
                                </a>
                            </td>
                        </tr>
                    <% 
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="5" align="center">No inventory assets populated in ledger.</td>
                        </tr>
                    <% } %>
                    </tbody>
                </table>
            </td>
        </tr>
    </table>

</body>
</html>