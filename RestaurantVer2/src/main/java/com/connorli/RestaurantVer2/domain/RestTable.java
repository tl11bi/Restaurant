package com.connorli.RestaurantVer2.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @SequenceGenerator(name="rest_table_id_gen", initialValue = 400, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rest_table_id_gen")
    private long tableID;
    @Column(name = "TABLE_NAME")
    private String tableName;
    @Column(name = "TABLE_CAPACITY")
    private int capacity;
    @Column(name = "TABLE_OCCUPIED")
    private boolean occupied;
    @OneToMany(mappedBy = "restTable")
    @JsonManagedReference
    private List<Order> orders = new ArrayList<>(10);
    @OneToMany(mappedBy = "restTable")
    @JsonManagedReference
    private List<Reservation> reservations;
    public RestTable(String tableName, int capacity) {
        this.tableName = tableName;
        this.capacity = capacity;
        this.occupied = false;
        new RestTable();
    }

    protected RestTable() {
        reservations = new ArrayList<>(10);
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

    public void setTableID(long tableID) {
        this.tableID = tableID;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
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
        return tableID == restTable.tableID && Objects.equals(tableName, restTable.tableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableID, tableName);
    }
}

