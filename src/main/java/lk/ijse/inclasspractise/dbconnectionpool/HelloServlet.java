package lk.ijse.inclasspractise.dbconnectionpool;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebServlet(name = "helloServlet", value = "/servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Context initContext = null;
        try {
            initContext = new InitialContext();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        Context envContext  = null;
        try {
            envContext = (Context)initContext.lookup("java:/comp/env");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        DataSource ds = null;
        try {
            ds = (DataSource)envContext.lookup("jdbc/myoracle");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection conn = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void destroy() {
    }
}