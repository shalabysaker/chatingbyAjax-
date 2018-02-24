/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shalaby
 */
public class messageServlet extends HttpServlet {

  public static  Vector<messageDto> allMessages = new Vector<messageDto>();
    public int  id = 0;
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         try (PrintWriter out = response.getWriter()) {

             out.write(buildGson(allMessages));
            
        }
       }
    private String buildGson( Vector<messageDto> all)
    {
        Gson g = new Gson();
        return g.toJson(all);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
         PrintWriter out = response.getWriter();
        messageDto temp = new messageDto();
        
        temp.setMsg(request.getParameter("msg"));
        temp.setName(request.getParameter("name"));
        temp.setMsgId(id++);
        
        allMessages.add(temp);
        
        System.out.println(allMessages.lastElement().getMsg());
   
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "that ok";
    }// </editor-fold>

}
