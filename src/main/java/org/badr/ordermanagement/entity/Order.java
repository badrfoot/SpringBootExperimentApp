/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import java.time.LocalDate;
import org.badr.ordermanagement.entity.enums.PaymentType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.AccessLevel;
import org.springframework.util.Assert;

/**
 *
 * @author oussama
 */

@Entity
@Table(name = "[order]")
@Access(AccessType.FIELD)
@lombok.NoArgsConstructor(access = AccessLevel.PRIVATE)
@lombok.Getter @lombok.Setter
public class Order {

	@EmbeddedId
    @lombok.Setter(AccessLevel.NONE)
	private OrderPrimaryKey orderPrimaryKey;

	@Column
    @lombok.Setter(AccessLevel.NONE)
	private Boolean canceled = false;

    @Column
    private Boolean completed = false;

    @Column
    private String cancellationReason;

	@Transient
    @lombok.Setter(AccessLevel.NONE) @lombok.Getter(AccessLevel.NONE)
    private Double totalPrice;

    @Column
    @javax.validation.constraints.NotNull(message = "La date de livraison ne doit pas être null")
    private LocalDate deliveryDate;

    @Column
    @Enumerated(EnumType.STRING)
    @lombok.Setter(AccessLevel.NONE) // lombok
    private PaymentType paymentType = PaymentType.Cash;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    @javax.validation.Valid
	@javax.validation.constraints.NotNull(message = "Les lignes de commandes doit être renseignées")
	@javax.validation.constraints.Size(min = 1, message = "Les lignes de commandes doit être renseignées")
    // TODO // Should not have getter and setter
    private List<OrderDetail> orderDetails = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "PAYMENT_CREDITCARD_ID")
    @javax.validation.Valid
    @lombok.Setter(AccessLevel.NONE)
	private CreditCard creditCard;

	@ManyToOne
	@JoinColumn(name = "BONUSCARD_ID_USED")
	private BonusCard bonusCard;


    public Order(Customer customer, LocalDate orderDate) {
        Assert.notNull(orderDate, "Le paramètre [orderDate] ne doit pas être null");
        Assert.notNull(customer, "Le paramètre [orderDate] ne doit pas être null");
        Assert.isTrue( !customer.getFirstName().isEmpty() && !customer.getLastName().isEmpty(),//
                       "Le [customer] doit avoir un nom et un prénom" );

        this.orderPrimaryKey = new OrderPrimaryKey(customer, orderDate);
    }

    /**
     * Calculate the total order price base on {@code orderDetails}
     * @return Total order price
     */
    public Double getTotalPrice() {
		totalPrice = orderDetails.stream()//
                                    .mapToDouble(OrderDetail::getOrderDetailTotalPrice)//
                                    .sum();
        return totalPrice;
    }

    /**
     *
     * @param creditCard
     */
    public void payWithCreditCard(CreditCard creditCard) {
        Assert.isTrue(completed == false, "Impossible de modifier l'état La Commande après la clôture");
        Assert.notNull(getOrderPrimaryKey().getCustomer(), "L'affectation du client doit précéder le payement");
        Assert.notNull(creditCard, "Paramètre [creditCard] ne doit pas être null");
        Assert.isTrue(getOrderPrimaryKey().getCustomer().doesCreditCardExist(creditCard),
                "Paramètre [creditCard] n'appartient pas au client");

        this.paymentType = PaymentType.CreditCard;
        this.creditCard = creditCard;
        if (this.bonusCard != null){
            bonusCard.addBonusPoints(20);
        }

        completeOrder();
    }

    /**
     *
     */
    public void payWithCash(){
        Assert.isTrue(completed == false, "Impossible de modifier l'état La Commande après la clôture");

        this.creditCard = null;
        this.paymentType = PaymentType.Cash;
        bonusCard.addBonusPoints(40);

        completeOrder();
    }

    /**
     *
     */
    private void completeOrder(){
        Assert.isTrue(completed == false, "Impossible de modifier l'état La Commande après la clôture");
        this.completed = true;
        orderDetails.stream().forEach(OrderDetail::completeOrderDetail);
    }

    /**
     *
     * @param product
     * @return
     */
    public Boolean doesProductExistInOrder(Product product){
        return orderDetails.stream()//
                            .anyMatch(orderDetail -> //
                                            orderDetail.getProduct().equals(product));
    }

    /**
     *
     * @param cancellationReason
     */
    public void cancel(String cancellationReason){
        Assert.isTrue(completed == false, "Impossible de modifier l'état La Commande après la clôture");
        Assert.hasText(cancellationReason, "La raison d'annulation de la commande doit être renseignée");

        this.getOrderDetails().forEach(OrderDetail::cancel);
        this.canceled = true;
        this.cancellationReason = cancellationReason;

        completeOrder();
    }

    public boolean mergeWithOrder(Order order) {
        Assert.notNull(order, "la paramètre [order] (à merger) ne doit pas être null");
        Assert.isTrue(completed == false && order.getCompleted() == false,
                "Impossible de modifier l'état La Commande après la clôture");

        boolean isMerged = true;

        if (getOrderPrimaryKey().getOrderDate()
                .compareTo(order.getOrderPrimaryKey().getOrderDate()) > 1) {
            isMerged = false;

        } else {
            this.orderDetails.addAll(order.getOrderDetails());
        }

        return isMerged;
    }

}