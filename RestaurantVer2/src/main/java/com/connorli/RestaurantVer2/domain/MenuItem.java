package com.connorli.RestaurantVer2.domain;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@NamedQueries({
        @NamedQuery(name = "REST_MENU_ITEM.findAll", query = "SELECT mt FROM REST_MENU_ITEM mt")
})
@Entity(name = "REST_MENU_ITEM")
@Table(name = "REST_MENU_ITEM")
public class MenuItem {
    @Id
    @Column(name = "MENU_ITEM_ID")
    @SequenceGenerator(name = "menu_id_gen", initialValue = 200, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_id_gen")
    private long menuItemID;
    @Column(name = "MENU_ITEM_NAME")
    private String menuItemName;
    @Column(name = "MENU_ITEM_PRICE")
    private BigDecimal price;
    @OneToMany(mappedBy="menuItem")
    private Set<OrderMenuItem> orderMenuItems;


    public MenuItem(String menuItemName, BigDecimal price) {
        this.menuItemName = menuItemName;
        this.price = price;
        new MenuItem();
    }


    protected MenuItem() {

        this.orderMenuItems = new HashSet<>(10);
    }

    //for relation table
    protected void addOrder(OrderMenuItem orderMenuItems){
        this.orderMenuItems.add(orderMenuItems);
    }

    protected void removeOrder(Order order){
        orderMenuItems.remove(order);
    }

    //property methods
    public long getMenuItemID() {
        return menuItemID;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "MenuItem{" +
                "menuItemID=" + menuItemID +
                ", menuItemName='" + menuItemName + '\'' +
                ", price=" + price +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return menuItemID == menuItem.menuItemID && menuItemName.equals(menuItem.menuItemName) && price.equals(menuItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItemID, menuItemName, price);
    }

}
