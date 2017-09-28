/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.util.Assert;

/**
 *
 * @author oussama
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderDetail extends AbstractBaseEntity {

    @NaturalId
    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(name = "ORDER_CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
        ,
			@JoinColumn(name = "ORDER_DATE", referencedColumnName = "ORDER_DATE")
    })
    @javax.validation.Valid
    @javax.validation.constraints.NotNull(message = "Une ligne de commande doit être liée à une commande")
    private Order order;

    @NaturalId
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    @lombok.Setter(AccessLevel.NONE)
    @javax.validation.Valid
    @javax.validation.constraints.NotNull(message = "Une ligne de commande doit avoir un produit")
    private Product product;

    @Column
    @javax.validation.constraints.Min(value = 1, message = "La quantité commandée doit au minumum 1")
    private float quantity;


    /**
     *
     * @param order
     * @param product
     * @param quantity
     */
    public OrderDetail(Order order, Product product, float quantity) {
        Assert.notNull(product, "le paramètre [product] ne doit pas être null");
        Assert.notNull(order, "le paramètre [order] ne doit pas être null");
        Assert.isTrue(quantity > 0, "le paramètre [quantity] doit être au moins = 1");
        Assert.isTrue(product.doesQuantityExist(quantity),
                String.format("Le stock ne contient pas la quantité demandée pour le produit %s", product.getName()));

        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public float getOrderDetailTotalPrice() {

        if (product == null) {
            return 0;
        }

        return product.getUnitPrice() * quantity;
    }

    public void completeOrderDetail(){
        if (order.getCompleted()){
            product.substractStoredQuantity(quantity);
        }
    }

}
