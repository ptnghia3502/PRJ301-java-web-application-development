package cuongnvt.Validation;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Utils {

    public static void ReqDis(HttpServletRequest request, HttpServletResponse response, String location, boolean method) throws ServletException, IOException {
        RequestDispatcher ReqDis = request.getRequestDispatcher(location);
        if (method) {
            ReqDis.forward(request, response);
            return;
        }
        ReqDis.include(request, response);

    }
    
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(name)) {
                    return cookies[i].getValue();
                }
            }
        }
        return null;
    }

    public static boolean setCookie(HttpServletResponse response, Cookie cookie, int maxAge) {
        boolean flag;
        try {
            cookie.setMaxAge(maxAge);
            response.addCookie(cookie);
            flag = true;
        } catch (NullPointerException ex) {
            flag = false;
        }
        return flag;
    }
    
    public static String getStringAttribute(HttpServletRequest request, HttpSession session, String name){
        String s;
        try{ 
        s = session.getAttribute(name).toString();
        }catch(NullPointerException ex){
           s = null;
        }
        return s;
    }
    
    public static String getStringAttribute(HttpServletRequest request, String name){
        String s;
        try{ 
        s = request.getAttribute(name).toString();
        }catch(NullPointerException ex){
           s = null;
        }
        return s;
    }
    
    public static int getIntAttribute(HttpServletRequest request, String name)
    throws NullPointerException, NumberFormatException {
      int z;  
      z = Integer.parseInt(request.getAttribute(name).toString());
      return z;  
    }
    
     public static int getIntAttribute(HttpServletRequest request, HttpSession session, String name)
    throws NullPointerException, NumberFormatException {
      int z;  
      z = Integer.parseInt(session.getAttribute(name).toString());
      return z;  
    }
}
