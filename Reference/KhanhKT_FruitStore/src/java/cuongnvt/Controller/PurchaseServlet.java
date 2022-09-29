package cuongnvt.Controller;

import cuongnvt.Cart.CartDTO;
import cuongnvt.Fruit.FruitDAO;
import cuongnvt.Fruit.FruitDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PurchaseServlet", urlPatterns = {"/PurchaseServlet"})
public class PurchaseServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter prW = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String fruitID;
        int purchaseNumber = 0;
        HttpSession session = request.getSession();
        RequestDispatcher ReqDis;
        try {
            fruitID = request.getAttribute("fruitID").toString();
         
        } catch (NullPointerException ex) {
            fruitID = "";
        }
        try {
            purchaseNumber = Integer.parseInt(request.getAttribute("purchaseNumber").toString().trim());
            if (purchaseNumber < 0){throw new NumberFormatException();}
        } catch (NullPointerException ex) {
            return;
        } catch (NumberFormatException ex) {
            request.setAttribute("purchaseStatus", "error");
            ReqDis = request.getRequestDispatcher("Welcome.jsp");
            ReqDis.forward(request, response);
            return;
        }
        
        ArrayList<CartDTO> carts = (ArrayList<CartDTO>)session.getAttribute("carts");
        if (carts == null){
        carts = new ArrayList<>();
        }
        FruitDAO dao = new FruitDAO();
        FruitDTO fruit = null;
        try {
            fruit = dao.getFruit(fruitID);
        } catch (SQLException | ClassNotFoundException ex) {
        }
        boolean allowtoadd = true;
        if (carts.isEmpty()){}
        else {
            for (CartDTO cart: carts){
                if (cart.getFruit().getID().equals(fruitID)){
                    int oldata = cart.getQuantity();
                    int newdata = oldata + purchaseNumber;
                    cart.setQuantity(newdata);
                    allowtoadd = false;
                    break;
                }
            } }
           if (allowtoadd){
               CartDTO cart = new CartDTO(fruit,purchaseNumber);
               carts.add(cart);
           } 
        session.setAttribute("carts", carts);
        request.setAttribute("purchaseStatus", "success");
        
         ReqDis = request.getRequestDispatcher("Welcome.jsp");
        ReqDis.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
