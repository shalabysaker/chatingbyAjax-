/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shalaby
 */
public class BasicServlet extends HttpServlet {

    Vector<Users> Alluser = new Vector<Users>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //coming from register

        Users tempUser = new Users();

        tempUser.setUserName(request.getParameter("username"));
        tempUser.setEmail(request.getParameter("email"));
        tempUser.setPassword(request.getParameter("password"));

        Alluser.add(tempUser);
        System.out.println(Alluser.size());

        response.sendRedirect("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // coming from login
        PrintWriter out = response.getWriter();
        String name = request.getParameter("userNameLogin");
        String pass = request.getParameter("passwordLogin");

        if (Alluser.isEmpty()) {
            
            response.sendRedirect("index.html");

        } else {
            for (Users one : Alluser) {
                if (one.getUserName().equals(name) && one.getPassword().equals(pass)) {
                   
                    //creating session
                    System.out.println("create session");
                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(1000);
                   
                    session.setAttribute("user", one);
                    RequestDispatcher dis = request.getRequestDispatcher("messageScreen.jsp");
                    dis.forward(request, response);

                } else {

                    //empty fileds and redirect to login again
                    System.out.println("no data matched");  
                   
                    RequestDispatcher dis = request.getRequestDispatcher("index.html");
                    dis.include(request, response);
                    
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
