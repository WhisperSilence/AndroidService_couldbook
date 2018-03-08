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

        // ���տͻ�����Ϣ
        String username = request.getParameter("username");
        username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
        String password = request.getParameter("password");

        System.out.println(username + "--" + password);

        // �½��������
        Service serv = new Service();

        // ��֤����
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

        // ������Ϣ���ͻ�
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
      //  out.println("�û�����" + username);
      //  out.println("����1��" + password);
        //out.print("���ݣ�" + content);
        out.print(code);
        
        out.flush();
        out.close();

    }
   /* public String getContent(String username, String password) {

        // ��ȡSql��ѯ���
        String logSql = "select * from notes where username ='" + username
                + "' and password ='" + password + "'";

        // ��ȡDB����
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        // ����DB����
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

        // ��ȡSql��ѯ���
    	System.out.println("----------1-----------");
        String logSql = "select * from users where username ='" + username
                + "' and password ='" + password + "'";

        // ��ȡDB����
        DBManager sql = DBManager.createInstance();
        sql.connectDB();

        // ����DB����
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