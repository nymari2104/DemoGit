/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catnm.tblProduct;

/**
 *
 * @author DELL
 */
public class TblProductError {
    private String quantityAndStatusErr;
    private String invalidQuantityErr;

    public TblProductError() {
    }

    /**
     * @return the quantityAndStatusErr
     */
    public String getQuantityAndStatusErr() {
        return quantityAndStatusErr;
    }

    /**
     * @param quantityAndStatusErr the quantityAndStatusErr to set
     */
    public void setQuantityAndStatusErr(String quantityAndStatusErr) {
        this.quantityAndStatusErr = quantityAndStatusErr;
    }

    /**
     * @return the negativeQuantityErr
     */
    public String getInvalidQuantityErr() {
        return invalidQuantityErr;
    }

    /**
     * @param invalidQuantityErr the negativeQuantityErr to set
     */
    public void setInvalidQuantityErr(String invalidQuantityErr) {
        this.invalidQuantityErr = invalidQuantityErr;
    }

    
}
