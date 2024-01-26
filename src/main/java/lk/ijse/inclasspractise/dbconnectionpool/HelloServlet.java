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
        try {
            Context initContext = new InitialContext();
            DataSource pool = (DataSource) initContext.lookup("java:/comp/env/jdbc/pos");

            Connection conn = pool.getConnection();

//            DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
//            Connection conn = ds.getConnection();
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void destroy() {
    }
}