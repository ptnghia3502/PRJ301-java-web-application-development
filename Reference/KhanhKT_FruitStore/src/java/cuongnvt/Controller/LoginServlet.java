package cuongnvt.Controller;

import cuongnvt.Account.AccountDAO;
import cuongnvt.Account.AccountDTO;
import static cuongnvt.Controller.DispatchServlet.SEARCH;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username;
        RequestDispatcher ReqDis;
        try {
            username = request.getAttribute("username").toString();
        } catch (NullPointerException ex) {
            username = "";
        }
        String password;
        try {
            password = request.getAttribute("password").toString();
        } catch (NullPointerException ex) {
            password = "";
        }

        // LẤY COOKIES
        String usernameCookie = "", passwordCookie = "";
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    usernameCookie = cookie.getValue();
                    break;

                }

            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("password")) {
                    passwordCookie = cookie.getValue();
                    break;

                }
            }
        }
            // LƯU BẰNG SESSION
            HttpSession session = request.getSession();
            String usernameSession;
            try {
                usernameSession = session.getAttribute("username").toString();
            } catch (NullPointerException ex) {
                usernameSession = "";
            }
            String passwordSession;
            try {
                passwordSession = session.getAttribute("password").toString();
            } catch (NullPointerException ex) {
                passwordSession = "";
            }

            AccountDAO dao = new AccountDAO();

            AccountDTO account = null, accountCookie = null, accountSession = null;
            String priority = "nothing";

            try {
                accountCookie = dao.getAccount(usernameCookie, passwordCookie);
                if (accountCookie != null) {
                    priority = "cookie";
                }

                accountSession = dao.getAccount(usernameSession, passwordSession);
                if (accountSession != null) {
                    priority = "session";
                }

                account = dao.getAccount(username, password);
                if (account != null) {
                    priority = "request";
                }
            } catch (SQLException | ClassNotFoundException ex) {
            }
            PrintWriter out = response.getWriter();
            
            if (priority.equals("nothing")){
                ReqDis = request.getRequestDispatcher("Login.jsp");
                ReqDis.forward(request, response);
            }
            
            
            if (priority.equals("cookie")) {
                // MÌNH CHƯA HỀ ĐĂNG NHẬP, NHƯNG ĐÃ CÓ COOKIE LƯU Ở SERVER
                session.setAttribute("account", accountCookie);
                session.setAttribute("username", usernameCookie);
                session.setAttribute("password", passwordCookie);
                ReqDis = request.getRequestDispatcher(SEARCH);
                ReqDis.forward(request, response);
            }

            if (priority.equals("session")) {
                 session.setAttribute("account", accountSession);
                ReqDis = request.getRequestDispatcher(SEARCH);
                ReqDis.forward(request, response);
            }

            if (priority.equals("request")) {
                 session.setAttribute("account", account);
                // MÌNH ĐÃ THỰC HIỆN QUÁ TRÌNH ĐĂNG NHẬP, BẤT KỂ COOKIE CÓ HAY LÀ KHÔNG
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                
                Cookie UCookie = new Cookie("username", username);
                Cookie PCookie = new Cookie("password", password);
                UCookie.setMaxAge(30);
                PCookie.setMaxAge(30);
                response.addCookie(UCookie);
                response.addCookie(PCookie);
                // TẠO COOKIE
                ReqDis = request.getRequestDispatcher(SEARCH);
                ReqDis.forward(request, response);
            }
        }
    

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
