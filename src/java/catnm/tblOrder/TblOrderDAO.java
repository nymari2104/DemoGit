/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catnm.tblOrder;

import catnm.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class TblOrderDAO implements Serializable {

    private List<TblOrderDTO> order;

    public List<TblOrderDTO> getOrder() {
        return order;
    }

    public int countOrder()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            //1. connect DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Select count(id) as [totalOrderId]"
                        + "From tblOrder";
                //3. create Statement object
                stm = con.prepareStatement(sql);
                //4. execute query
                rs = stm.executeQuery();
                //5. process Resultset
                if (rs.next()) {
                    count = rs.getInt("totalOrderId");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return count;
    }

    public boolean createOrder(String customer, String address, String email, Date date)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. connect DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Insert into tblOrder(id, date, customer, address, email) "
                        + "values(?,?,?,?,?)";
                //3. create Statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, "0d" + String.format("%03d", countOrder()));
                stm.setDate(2, date);
                stm.setString(3, customer);
                stm.setString(4, address);
                stm.setString(5, email);
                //4. execute query
                int affectRow = stm.executeUpdate();
                //5. process Resultset
                if (affectRow > 0) {
                    // reduce quantity of product
                    result = true;
                }
            }//con has already been available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public void checkout(String customer, String address, String email, Date date)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. connect DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Select id, date, customer, address, email "
                        + "From tblOrder "
                        + "Where date=? and customer=? and address=? and email=?";
                //3. create Statement object
                stm = con.prepareStatement(sql);
                stm.setDate(1, date);
                stm.setString(2, customer);
                stm.setString(3, address);
                stm.setString(4, email);
                //4. execute query
                rs = stm.executeQuery();
                //5. process Resultset
                while (rs.next()) {
                    //map data
                    //get data from Resultset
                    String id = rs.getString("id");
                    Date getDate = rs.getDate("date");
                    String getCustomer = rs.getString("customer");
                    String getAddress = rs.getString("address");
                    String getEmail = rs.getString("email");
                    TblOrderDTO dto = new TblOrderDTO(id, getDate, getCustomer, getAddress, getEmail, 0);
                    if (this.order == null) {
                        this.order = new ArrayList<>();
                    }//order is not available
                    this.order.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
