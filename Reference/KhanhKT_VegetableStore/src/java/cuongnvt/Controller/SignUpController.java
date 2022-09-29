package cuongnvt.Controller;

import cuongnvt.Account.AccountDAO;
import cuongnvt.Account.AccountDTO;
import cuongnvt.Validation.FaultBlock;
import cuongnvt.Validation.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SignUpController", urlPatterns = {"/SignUpController"})
public class SignUpController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        FaultBlock fb = new FaultBlock();
        String fromLogIn = request.getParameter("Sign Up");
        if (fromLogIn != null) {
            Utils.ReqDis(request, response, "SignUp.jsp", true);
            return;
        }
        String fromSignUp = request.getParameter("Sign Up 2");
        if (fromSignUp == null) {
            Utils.ReqDis(request, response, "SignUp.jsp", true);
            return;
        }
        String Username = request.getParameter("Username");
        String CheckRole = request.getParameter("Role");
        String Password = request.getParameter("Password");
        String PasswordConfirm = request.getParameter("PasswordConfirm");
        String FullName = request.getParameter("FullName");

        fb.formatCheck(Username, 6, 20, "Username", true);
        fb.formatCheck(Password, 6, 20, "Password", true);
        fb.formatCheck(PasswordConfirm, 0, 0, "Confirmed Password", false);
        fb.formatCheck(FullName, 2, 40, "Full Name", true);
        fb.SimilarityCheck(Password, PasswordConfirm, "Password", "Confirmed Password");

        if (!fb.getList().isEmpty()) {
            request.setAttribute("FaultBlock", fb);
            Utils.ReqDis(request, response, "SignUp.jsp", true);
            return;
        }
      
        boolean insertCheck = false;
        boolean Role = false;
        if (CheckRole != null){Role = true;}
        try {
            insertCheck = AccountDAO.insertAccount(Username, Password, Role, FullName);
        } catch (SQLException | ClassNotFoundException ex) {
        }
        if (!insertCheck) {
            String x = Username + " has been existed. Please try again with another Username.";
            fb.getList().add(x);
            request.setAttribute("FaultBlock", fb);
            Utils.ReqDis(request, response, "SignUp.jsp", true);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("Username", Username);
        session.setAttribute("Password", Password);
        Cookie CookieObjectUsername = new Cookie("Username", Username);
        Cookie CookieObjectPassword = new Cookie("Password", Password);
        Utils.setCookie(response, CookieObjectUsername, 30);
        Utils.setCookie(response, CookieObjectPassword, 30);

        AccountDTO acc = null;
        try {
            acc = AccountDAO.getAccount(Username, Password);
        } catch (SQLException | ClassNotFoundException ex) {
        }
        session.setAttribute("account", acc);
        Utils.ReqDis(request, response, "Welcome.jsp", true);

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
