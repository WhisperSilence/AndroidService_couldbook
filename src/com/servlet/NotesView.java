
package com.servlet;


import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Fc;
public class NotesView extends HttpServlet {
        /**
         * 
         */
	
	 List<Fc> Albums = new ArrayList<Fc>();
     Fc fc = new Fc(null, null, null);

        private static final long serialVersionUID = 369840050351775312L;

        // 为客户端的get请求作响应
        protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
           
          String username = request.getParameter("username");
          username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
          System.out.println("----------3-----------");
          System.out.println(username + "---");
          String logSql = "select * from notes where username ='" + username
                  + "' ";
          // 获取DB对象
          DBManager sql = DBManager.createInstance();
          sql.connectDB();

          // 操作DB对象
          try {
          	String content = "";
              ResultSet rs = sql.executeQuery(logSql);
              int i=0;
              while (rs.next()) {
                  i++;
                  content = rs.getString("content");
                  
                  fc.setUser(username);
                  //fc.setName("xiaozhou");
                  fc.setContext(content);
                  Albums.add(fc);
                 
              }
              sql.closeDB();
          } catch (SQLException e) {
              e.printStackTrace();
              sql.closeDB();
          }
          
          sendObject( Albums,response);
        
        }
        
        protected void sendObject(Object obj, HttpServletResponse response)
        {
           try
           {  //Fc Fc = new Fc();
               OutputStream os = response.getOutputStream();
               ObjectOutputStream oos = new ObjectOutputStream(os);
               oos.writeObject(obj);
           }
           catch (Exception e)
           {
               e.printStackTrace();
           }
        

            
}
}


