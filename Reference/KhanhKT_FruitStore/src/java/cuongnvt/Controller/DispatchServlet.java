package cuongnvt.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DispatchServlet extends HttpServlet {

    final static String LOGIN = "LoginServlet";
    final static String DIS = "DispatchServlet";
    final static String LOGOUT = "LogoutServlet";
    final static String SEARCH = "SearchServlet";
    final static String PURCHASE = "PurchaseServlet";
       final static String CART = "CartServlet";
       final static String REMOVECART = "DeleteCartServlet";    
       final static String PAY = "PayServlet";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter prW = response.getWriter();
        RequestDispatcher ReqDis;
        response.setContentType("text/html;charset=UTF-8");
        String btAction = request.getParameter("btAction");
        HttpSession session = request.getSession();
        if (btAction == null) {
            btAction = "Submit";
        }

        if (btAction.equals("Submit")) {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            request.setAttribute("username", username);
            request.setAttribute("password", password);

            ReqDis = request.getRequestDispatcher(LOGIN);
            ReqDis.forward(request, response);
        }
        if (btAction.equals("Log out")) {
            ReqDis = request.getRequestDispatcher(LOGOUT);
            ReqDis.forward(request, response);
        }

        if (btAction.equals("Search") || btAction.equals("Back")) {
            String search = request.getParameter("txtSearch");
            session.setAttribute("search", search);
            ReqDis = request.getRequestDispatcher(SEARCH);
            ReqDis.forward(request, response);
        }
        if (btAction.equals("Purchase")) {
            String fruitID = request.getParameter("fruitID");
            String purchaseNumber = request.getParameter("purchaseNumber");
            request.setAttribute("fruitID", fruitID);
            request.setAttribute("purchaseNumber", purchaseNumber);
            ReqDis = request.getRequestDispatcher(PURCHASE);
            ReqDis.forward(request, response);
        }
        if (btAction.equals("Check my cart")) {
            
            ReqDis = request.getRequestDispatcher(CART);
            ReqDis.forward(request, response);
        }
        if (btAction.equals("Remove")) {
            String fruitID = request.getParameter("fruitID");
            request.setAttribute("fruitID", fruitID);
            ReqDis = request.getRequestDispatcher(REMOVECART);
            ReqDis.forward(request, response);
        }
   if (btAction.equals("Pay")) {
            
            ReqDis = request.getRequestDispatcher(PAY);
            ReqDis.forward(request, response);
        }
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
