package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.InventoryItem;

@WebServlet("/FoodBankController")
public class FoodBankController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // In-memory global state tracking substituting physical DB transactions
    private static List<InventoryItem> inventoryDbTable = new ArrayList<>();
    private static int primaryKeySequence = 1;

    static {
        // Populating mockup mock arrays directly matching your storyboard definitions
        inventoryDbTable.add(new InventoryItem(primaryKeySequence++, "Instant Noodles (Pack of 5)", 150, "2027-12-01"));
        inventoryDbTable.add(new InventoryItem(primaryKeySequence++, "Canned Sardines (150g)", 200, "2028-04-15"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) action = "dashboard";
        
        HttpSession session = request.getSession(false);
        
        // Guard check protecting endpoints against unauthenticated session states
        if (session == null || session.getAttribute("loggedUser") == null) {
            if (!action.equals("login") && !action.equals("register")) {
                response.sendRedirect("login.jsp");
                return;
            }
        }

        switch (action) {
            case "logout":
                if (session != null) session.invalidate();
                response.sendRedirect("login.jsp");
                break;
                
            case "manageInventory":
                request.setAttribute("itemsList", inventoryDbTable);
                request.getRequestDispatcher("HepInventory.jsp").forward(request, response);
                break;
                
            case "deleteItem":
                int targetId = Integer.parseInt(request.getParameter("id"));
                inventoryDbTable.removeIf(item -> item.getId() == targetId);
                response.sendRedirect("FoodBankController?action=manageInventory");
                break;
                
            case "dashboard":
            default:
                request.getRequestDispatcher("hep_staff.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            String usernameInput = request.getParameter("username");
            String roleInput = request.getParameter("role"); // User selection assignment
            
            User activeUser = new User();
            activeUser.setEmail(usernameInput);
            activeUser.setFullName("Authorized System User");
            activeUser.setRole(roleInput != null ? roleInput : "hep_staff");
            
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", activeUser);
            
            // Dynamic redirection target based on active structural context
            if ("student".equalsIgnoreCase(roleInput)) {
                response.sendRedirect("student.jsp");
            } else if ("donor".equalsIgnoreCase(roleInput)) {
                response.sendRedirect("donor.jsp");
            } else {
                response.sendRedirect("FoodBankController?action=dashboard");
            }
            
        } else if ("addItem".equals(action)) {
            String itemName = request.getParameter("item_name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String expiryDate = request.getParameter("expiry_date");
            
            InventoryItem newItem = new InventoryItem(primaryKeySequence++, itemName, quantity, expiryDate);
            inventoryDbTable.add(newItem);
            
            response.sendRedirect("FoodBankController?action=manageInventory");
        }
    }
}