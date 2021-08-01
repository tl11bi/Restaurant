package com.connorli.RestaurantVer2.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity(name="REST_ORDER_MENU_ITEM")
@Table(name="REST_ORDER_MENU_ITEM")
public class OrderMenuItem {
    @Id
    @Column(name = "OMI_ID")
    @SequenceGenerator(name = "omi_id_gen", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "omi_id_gen")
    private long orderMenuItemID;
    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    @JsonBackReference
    private Order order;
    @ManyToOne
    @JoinColumn(name = "MENU_ITEM_ID")
    @JsonBackReference
    private MenuItem menuItem;

    public OrderMenuItem(Order order, MenuItem menuItem) {
        this.orderMenuItemID = orderMenuItemID;
        this.order = order;
        this.menuItem = menuItem;
    }

    protected OrderMenuItem() {

    }

    public long getOrderMenuItemID() {
        return orderMenuItemID;
    }

    public void setOrderMenuItemID(long orderMenuItemID) {
        this.orderMenuItemID = orderMenuItemID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderMenuItem)) return false;
        OrderMenuItem that = (OrderMenuItem) o;
        return orderMenuItemID == that.orderMenuItemID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderMenuItemID);
    }
}
