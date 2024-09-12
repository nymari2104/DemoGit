/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catnm.orderDetail;

import catnm.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
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
public class OrderDetailDAO implements Serializable {

    private List<OrderDetailDTO> orderDetail;

    public List<OrderDetailDTO> getOrderDetail(String orderId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. connect DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Select id, productId, unitprice, quantity, orderId, total "
                        + "From OrderDetail "
                        + "Where orderId=?";
                //3. create Statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, orderId);
                //4. execute query
                rs = stm.executeQuery();
                //5. process Resultset
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String productId = rs.getString("productId");
                    float unitPrice = rs.getFloat("unitprice");
                    int quantity = rs.getInt("quantity");
                    String getOrderId = rs.getString("orderId");
                    float total = rs.getFloat("total");
                    OrderDetailDTO dto = new OrderDetailDTO(id, productId, unitPrice, quantity, getOrderId, total);
                    if (this.orderDetail == null) {
                        this.orderDetail = new ArrayList<>();
                    }//orderDetail is not available
                    this.orderDetail.add(dto);
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
        return orderDetail;
    }

    public boolean createOrderDetail(String productId, float unitPrice, int quantity, String orderId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. connect DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create sql string
                String sql = "insert into OrderDetail(productId, unitprice, quantity, orderId, total) "
                        + "values(?,?,?,?,?)";
                //3. create Statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, productId);
                stm.setFloat(2, unitPrice);
                stm.setInt(3, quantity);
                stm.setString(4, orderId);
                stm.setFloat(5, quantity * unitPrice);
                //4. execute query
                int affectedRows = stm.executeUpdate();
                //5. process Resultset
                if (affectedRows > 0) {
                    result = true;
                }
            }
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
}
