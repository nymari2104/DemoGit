/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catnm.tblProduct;

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
public class TblProductDAO implements Serializable {

    private List<TblProductDTO> product;

    public List<TblProductDTO> getProduct()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. connect DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create sql string
                String sql = "Select sku, name, description, quantity, price, status "
                        + "From tblProduct";
                //3. Create statement object
                stm = con.prepareStatement(sql);
                //4. execute query
                rs = stm.executeQuery();
                //5. process Resultset
                while (rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    boolean status = rs.getBoolean("status");
                    TblProductDTO dto = new TblProductDTO(sku, name, description, quantity, price, status);
                    if (this.product == null) {
                        this.product = new ArrayList<>();
                    }
                    this.product.add(dto);
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
        return this.product;
    }

    public TblProductDTO searchById(String id)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TblProductDTO result = null;
        try {
            //1. connect DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create sql string
                String sql = "Select sku, name, description, quantity, price, status "
                        + "From tblProduct "
                        + "Where sku=?";
                //3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4. execute query
                rs = stm.executeQuery();
                //5. process Resultset
                if (rs.next()) {
                    String sku = rs.getString("sku");
                    String productId = rs.getString("name");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    boolean status = rs.getBoolean("status");
                    result = new TblProductDTO(sku, productId, description, quantity, price, status);
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
        return result;
    }

    public boolean updateQuantity(String id, int quantity)
    throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try{
        //1. connect DB
        con = DBHelper.getConnection();
            if (con != null) {
        //2. create sql string
        String sql = "Update tblProduct "
                + "Set quantity=? "
                + "Where sku=?";
        //3. create statement object
        stm = con.prepareStatement(sql);
        stm.setInt(1, quantity);
        stm.setString(2, id);
        //4. execute query
        int affectedRow = stm.executeUpdate();
        //5. process result
                if (affectedRow > 0) {
                    result = true;
                }
        }
        }finally{
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
