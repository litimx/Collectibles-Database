package collections.models;

import collections.security.AppUser;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Collection {
    private int id;
    private String name;
    private AppUser user;

    private BigDecimal value;
    private List<Item> items;

    public Collection() {
    }

    public Collection(int id, String name, AppUser user, BigDecimal value, List<Item> items) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.value = value;
        this.items = items;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
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

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(user, that.user) && Objects.equals(value, that.value) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user, value, items);
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", value=" + value +
                ", items=" + items +
                '}';
    }
}
