package ua.goit.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.*;

import javax.persistence.*;
import javax.persistence.Cacheable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cook")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@PrimaryKeyJoinColumn(name = "employee_id", referencedColumnName = "id")
public class Cook extends Employee {

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @Fetch(FetchMode.SELECT)
    private List<CookedDish> dishes;

    public Cook() {
    }

    public Cook(String name, String secondName, Date birthDate, String phone, Integer salary, Position position, List<CookedDish> dishes) {
        super(name, secondName, birthDate, phone, salary);
        this.position = position;
        this.dishes = dishes;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<CookedDish> getDishes() {
        return dishes;
    }

    public void setDishes(List<CookedDish> dishes) {
        this.dishes = dishes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Cook cook = (Cook) o;

        if (position != cook.position) return false;
        return dishes != null ? dishes.equals(cook.dishes) : cook.dishes == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        String s = super.toString() +
                "position=" + position + ",\n" +
                "dishes=" + dishes + '\n';
        return s;
    }
}
