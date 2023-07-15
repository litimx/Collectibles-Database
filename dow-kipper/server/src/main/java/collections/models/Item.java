package collections.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private int id;
    private String name;
    private BigDecimal value;

    private Grade grade;

    public Item() {
    }

    public Item(int id, String name, BigDecimal value, Grade grade) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && Objects.equals(name, item.name) && Objects.equals(value, item.value) && grade == item.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, grade);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", grade=" + grade +
                '}';
    }
}
