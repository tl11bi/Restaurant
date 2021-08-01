package com.connorli.RestaurantVer2.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
        @NamedQuery(name = "REST_ORDER.findAll", query = "SELECT o FROM REST_ORDER o")
})
@Table(name = "REST_ORDER")
@Entity(name = "REST_ORDER")
public class Order {
    @Id
    @Column(name = "ORDER_ID")
    @SequenceGenerator(name = "order_id_gen", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_gen")
    private long orderID;
    @Column(name = "ORDER_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @ManyToOne
    @JoinColumn(name = "EMP_ID", nullable = false)
    @JsonBackReference
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "TABLE_ID", nullable = false)
    @JsonBackReference
    private RestTable restTable;
    @OneToMany(mappedBy="order")
    private Set<OrderMenuItem> orderMenuItems;

    public Order(Employee employee, RestTable restTable, Date time) {
        this.employee = employee;
        this.restTable = restTable;
        this.time = time;
        new Order();
    }

    protected Order() {
        orderMenuItems = new HashSet<>(10);
    }


    //relation table method
    public void addOrderItem(OrderMenuItem... orderMenuItems) {
        this.orderMenuItems.addAll(Arrays.asList(orderMenuItems));
    }


    public void removeOrderItem(int index) {
        this.orderMenuItems.remove(index);
    }
    //property method

    public BigDecimal getTotalPrice() {
        return BigDecimal.ZERO;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public RestTable getRestTable() {
        return restTable;
    }

    public void setRestTable(RestTable restTable) {
        this.restTable = restTable;
    }

    public Set<OrderMenuItem> getMenuItems() {
        return orderMenuItems;
    }

    public void setMenuItems(Set<OrderMenuItem> orderMenuItems) {
        this.orderMenuItems = orderMenuItems;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", employee=" + employee +
                ", restTable=" + restTable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderID == order.orderID && employee.equals(order.employee) && restTable.equals(order.restTable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, employee, restTable);
    }
}
