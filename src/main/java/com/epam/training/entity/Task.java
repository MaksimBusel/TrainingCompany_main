package main.java.com.epam.training.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Task implements Identifable, Serializable {
    private long id;
    private String name;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Task(long id, String name, String dateFrom, String dateTo) {
        this.id = id;
        this.name = name;
        this.dateFrom = LocalDate.parse(dateFrom);
        this.dateTo = LocalDate.parse(dateTo);
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = LocalDate.parse(dateFrom);
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = LocalDate.parse(dateTo);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Task task = (Task) o;
        return id == task.id &&
                name != null ?name.equals(task.name) : task.name == null &&
                dateFrom != null ? dateFrom.equals(task.dateFrom) : task.dateFrom == null &&
                dateTo != null ? dateTo.equals(task.dateTo) : task.dateTo == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateFrom, dateTo);
    }

    @Override
    public String toString() {
        return "Task{" +"id=" + id + ", name='" + name +", dateFrom='" + dateFrom + ", dateTo='" + dateTo +'}';
    }
}
