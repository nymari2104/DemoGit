/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catnm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author DELL
     */
public class DBHelper {
    public static Connection getConnection()
        throws /*ClassNotFoundException*/ NamingException, SQLException{
        
        //1. get current context
        Context context = new InitialContext();
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        //2. lookup DataSource
        /*javax.sql*/DataSource ds = (DataSource)tomcatContext.lookup("DS2106");
        //3. open connection
        Connection con = ds.getConnection();
        //4. return connection
        return con;
        
//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create connection string - protocol://ip:port;databaseName=
//        String url = "jdbc:sqlserver://" //protocol
//                + "localhost:1433;" //container
//                + "databaseName=PRJ301";
//        //3. Get connection from Driver Manager
//        Connection con = DriverManager.getConnection(url, "SA", "12345");
//        //4. return connection
//        return con;
    }
}
