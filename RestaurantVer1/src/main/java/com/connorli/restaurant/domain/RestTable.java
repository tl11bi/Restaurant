package com.connorli.restaurant.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "REST_TABLE.findAll", query = "SELECT t FROM REST_TABLE t")
})
@Table(name = "REST_TABLE")
@Entity(name = "REST_TABLE")
public class RestTable {
    @Id
    @Column(name = "TABLE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rest_table_id_gen")
    @SequenceGenerator(name="rest_table_id_gen", sequenceName="rest_table_seq", allocationSize = 1)
    private long tableID;

    @Column(name = "TABLE_NAME")
    private String tableName;
    @Column(name = "TABLE_CAPACITY")
    private int capacity;
    @Column(name = "TABLE_OCCUPIED")
    private boolean occupied;
    @OneToMany(mappedBy = "restTable")
    private List<Order> orders = new ArrayList<>(10);
    @OneToMany(mappedBy = "restTable")
    private List<Reservation> reservations = new ArrayList<>(10);
    public RestTable(String tableName, int capacity) {
        this.tableName = tableName;
        this.capacity = capacity;
        this.occupied = false;
    }

    public RestTable() {

    }

    //for relation table
    protected void addOrder(Order order){
        orders.add(order);
    }

    protected void removeOrder(Order order){
        orders.remove(order);
    }


    //for relation table
    protected void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    protected void removeReservation(Reservation reservation){
        reservations.remove(reservation);
    }

    //Property method
    public long getTableID() {
        return tableID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return "RestTable{" +
                "tableID=" + tableID +
                ", tableName='" + tableName + '\'' +
                ", capacity=" + capacity +
                ", occupied=" + occupied +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestTable)) return false;
        RestTable restTable = (RestTable) o;
        return tableID == restTable.tableID && capacity == restTable.capacity && occupied == restTable.occupied && tableName.equals(restTable.tableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableID, tableName, capacity, occupied);
    }
}



