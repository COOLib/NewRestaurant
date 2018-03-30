package ua.goit.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "cooked_dish")
public class CookedDish {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "dish_number")
    private Integer number;

    @Column(name = "dish_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Cook cook;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "data")
    private Date date;

    public CookedDish() {
    }

    public CookedDish(Integer id, Cook cook, Integer orderNumber, Date date) {
        this.id = id;
        this.cook = cook;
        this.orderNumber = orderNumber;
        this.date = date;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CookedDish that = (CookedDish) o;

        if (!id.equals(that.id)) return false;
        if (cook != null ? !cook.equals(that.cook) : that.cook != null) return false;
        if (orderNumber != null ? !orderNumber.equals(that.orderNumber) : that.orderNumber != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = cook != null ? cook.hashCode() : 0;
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CookedDish{" +
                "number=" + number +
                ", id=" + id +
                ", cook=" + cook +
                ", orderNumber=" + orderNumber +
                ", date=" + date +
                '}';
    }
}