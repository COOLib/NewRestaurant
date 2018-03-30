package ua.goit.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "order_number")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Waiter waiter;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "dish_orders",
            joinColumns = @JoinColumn(name = "order_number"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishes;

    @Column(name = "table_number")
    private Integer tableNumber;

    @Column(name = "date")
    private Date date;

    @Column(name = "is_closed")
    private String isClosed;

    public Order() {}

    public Order(Waiter waiter, List<Dish> dishes, Integer tableNumber, Date date, String isClosed) {
        this.waiter = waiter;
        this.dishes = dishes;
        this.tableNumber = tableNumber;
        this.date = date;
        this.isClosed = isClosed;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(String isClosed) {
        this.isClosed = isClosed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (waiter != null ? !waiter.equals(order.waiter) : order.waiter != null) return false;
        if (dishes != null ? !dishes.equals(order.dishes) : order.dishes != null) return false;
        if (tableNumber != null ? !tableNumber.equals(order.tableNumber) : order.tableNumber != null) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        return isClosed != null ? isClosed.equals(order.isClosed) : order.isClosed == null;

    }

    @Override
    public int hashCode() {
        int result = waiter != null ? waiter.hashCode() : 0;
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        result = 31 * result + (tableNumber != null ? tableNumber.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (isClosed != null ? isClosed.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number=" + number +
                ", waiter=" + waiter +
                ", dishes=" + dishes +
                ", tableNumber=" + tableNumber +
                ", date=" + date +
                ", isClosed='" + isClosed + '\'' +
                '}';
    }
}