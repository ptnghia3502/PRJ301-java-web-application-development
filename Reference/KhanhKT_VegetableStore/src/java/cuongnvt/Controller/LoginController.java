/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongnvt.Controller;

import cuongnvt.Account.AccountDAO;
import cuongnvt.Account.AccountDTO;
import cuongnvt.Validation.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        String Username = request.getParameter("Username");
        String Password = request.getParameter("Password");
        String CookieUsername = Utils.getCookieValue(request, "Username");
        String CookiePassword = Utils.getCookieValue(request, "Password");
        String SessionUsername = Utils.getStringAttribute(request, session, "Username");
        String SessionPassword = Utils.getStringAttribute(request, session, "Password");

        if ((Username == null || Password == null)
                && (CookieUsername == null || CookiePassword == null)
                && (SessionUsername == null || SessionPassword == null)) {
              Utils.ReqDis(request, response, "LogIn.jsp", true);
            return;
        }

        String priority = "nothing";
        AccountDTO CookieAccount = null;
        try {
            CookieAccount = AccountDAO.getAccount(CookieUsername, CookiePassword);
        } catch (SQLException | ClassNotFoundException e) {
        } catch (NullPointerException e) {
            CookieAccount = null;
        }
        if (CookieAccount != null) {
            priority = "cookie";
        }

        AccountDTO SessionAccount = null;

        try {
            SessionAccount = AccountDAO.getAccount(SessionUsername, SessionPassword);
        } catch (SQLException | ClassNotFoundException e) {
        } catch (NullPointerException e) {
            SessionAccount = null;
        }
        if (SessionAccount != null) {
            priority = "session";
        }

        AccountDTO account = null;
        try {
            account = AccountDAO.getAccount(Username, Password);
        } catch (SQLException | ClassNotFoundException e) {
        } catch (NullPointerException e) {
            account = null;
        }
        if (account != null) {
            priority = "request";
        }

        if (priority.equals("nothing")) {
            request.setAttribute("fault_LogIn", "Username_Password_Incorrect");
            Utils.ReqDis(request, response, "LogIn.jsp", true);
            return;
        }

        if (priority.equals("cookie")) {
            session.setAttribute("account", CookieAccount);
            session.setAttribute("Username", CookieUsername);
            session.setAttribute("Password", CookiePassword);
            Utils.ReqDis(request, response, "Welcome.jsp", true);
            return;
        }

        if (priority.equals("session")) {
            Utils.ReqDis(request, response, "Welcome.jsp", true);
            return;
        }
        if (priority.equals("request")) {
            session.setAttribute("account", account);
            session.setAttribute("Username", Username);
            session.setAttribute("Password", Password);

            Cookie CookieObjectUsername = new Cookie("Username", Username);
            Cookie CookieObjectPassword = new Cookie("Password", Password);
            Utils.setCookie(response, CookieObjectUsername, 30);
            Utils.setCookie(response, CookieObjectPassword, 30);

            Utils.ReqDis(request, response, "Welcome.jsp", true);
            return;
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
