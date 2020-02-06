package main.java.com.epam.training.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Course implements Identifable, Serializable {
    private long id;
    private String name;
    private String description;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String teacherId;

    public Course(long id, String name, String description, String dateFrom, String dateTo, String teacherId) {
        this.id = id;
        this.teacherId = teacherId;
        this.name = name;
        this.description = description;
        this.dateFrom = LocalDate.parse(dateFrom);
        this.dateTo = LocalDate.parse(dateTo);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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
        Course course = (Course) o;
        return id == course.id &&
                name != null ? name.equals(course.name) : course.name == null &&
                description != null ? description.equals(course.description) : course.description == null &&
                dateFrom != null ? dateFrom.equals(course.dateFrom) : course.dateFrom == null &&
                dateTo != null ? dateTo.equals(course.dateTo) : course.dateTo == null &&
                teacherId== course.teacherId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, dateFrom, dateTo, teacherId);
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name='" + name + ", description='" + description +
                ", dateFrom='" + dateFrom +  ", dateTo='" + dateTo + '}';
    }
}
