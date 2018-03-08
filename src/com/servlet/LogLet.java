package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBManager;
import com.service.Service;

public class LogLet extends HttpServlet {

    private static final long serialVersionUID = 369840050351775312L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 接收客户端信息
        String username = request.getParameter("username");
        username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
        String password = request.getParameter("password");

        System.out.println(username + "--" + password);

        // 新建服务对象
        Service serv = new Service();

        // 验证处理
        boolean loged = serv.login(username, password);
        if (loged) {
            System.out.println("Succss");
            request.getSession().setAttribute("username", username);
            // response.sendRedirect("welcome.jsp");
        } else {
            System.out.println("Failed");
        }
//***********************************************/
      //  String content = getContent(username,password);
        String code = getCode(username,password);
        //*******************************/

        // 返回信息到客户
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
      //  out.println("用户名：" + username);
      //  out.println("密码1：" + password);
        //out.print("内容：" + content);
        out.print(code);
        
        out.flush();
        out.close();

    }
   /* public String getContent(String username, String password) {

        // 获取Sql查询语句
        String logSql = "select * from notes where username ='" + username
                + "' and password ='" + password + "'";

        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        // 操作DB对象
        try {
        	String content = "";
            ResultSet rs = sql.executeQuery(logSql);
            if (rs.next()) {
            	
               content = rs.getString("content");
                sql.closeDB();
                return content;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.closeDB();
        return "nothing";
    }
    */
    public String getCode(String username, String password) {

        // 获取Sql查询语句
    	System.out.println("----------1-----------");
        String logSql = "select * from users where username ='" + username
                + "' and password ='" + password + "'";

        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        // 操作DB对象
        try {
        	
            ResultSet rs = sql.executeQuery(logSql);
            if (rs.next()) {
                sql.closeDB();
               	System.out.println("code=1");
                return "1";
            }else {
            	sql.closeDB();
               	System.out.println("code=0");
                return "0";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.closeDB();
        return "nothing";
    }
   
    

}