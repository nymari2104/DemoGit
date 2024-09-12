/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catnm.cart;

import catnm.tblProduct.TblProductDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class CartBean implements Serializable {

    private Map<TblProductDTO, Integer> items;

    public Map<TblProductDTO, Integer> getItems() {
        return items;
    }

    public void addItemToCart(TblProductDTO item, int quantity) {
        if (item == null) {
            return;
        }
//        if (item.trim().isEmpty()) {
//            return;
//        }
        //1. check existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }//item have existed
        //2. check existed item
//        int quantity = 1;
        TblProductDTO tmpProduct = null;
        for (TblProductDTO product : this.items.keySet()) {
            if (product.getSku().equals(item.getSku())) {
                //increment the quantity
                quantity = this.items.get(product) + quantity;
                tmpProduct = product;
            }
        }
//        if (this.items.containsKey(item)) {
//            //increment the quantity
//            quantity = this.items.get(item) + 1;
//        }//item has existed
        //3. drop item to cart
        if (tmpProduct != null) {
            this.items.remove(tmpProduct);
        }//item has existed
        this.items.put(item, quantity);
    }

    public void removeItemFromCart(TblProductDTO item) {
        if (item == null) {
            return;
        }
//        if (item.trim().isEmpty()) {
//            return;
//        }
        //1. check existed items
        if (this.items == null) {
            return;
        }
        //2. check exiested item
        TblProductDTO tmpProduct = null;
        for (TblProductDTO product : this.items.keySet()) {
            if (product.getSku().equals(item.getSku())) {
                tmpProduct = product;
            }
        }
        if (tmpProduct != null) {
            this.items.remove(tmpProduct);
            if (this.items.isEmpty()) {
                this.items = null;
            }//if items has noting
        }//item has existed
//        if (this.items.containsKey(item)) {
//            //3. remove item from cart
//            this.items.remove(item);
//            if (this.items.isEmpty()) {
//                this.items = null;
//            }//if items has nothing
//        }//item has existed
    }
    
    public int getQuantityOfItem(TblProductDTO item){
        int quantity = 0;
        if (item == null) {
            return quantity;
        }
        //1. check existed items
        if (this.items == null) {
            return quantity;
        }
        //2. check existed item
        for (TblProductDTO product : this.items.keySet()) {
            if (product.getSku().equals(item.getSku())) {
                //3. take quantity of item
                quantity = this.items.get(product);
            }//item has existed
        }
        return quantity;
    }
}
