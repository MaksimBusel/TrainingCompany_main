package main.java.com.epam.training.dto;

import main.java.com.epam.training.entity.Identifable;

public class CourseDto implements Identifable {
    private long id;
    private String name;
    private String description;
    private String dateFrom;
    private String dateTo;
    private String teacherFirstName;
    private String teacherLastName;
    private long teacherId;

    public CourseDto(long id, String name, String description, String dateFrom, String dateTo, String teacherFirstName,
                     String teacherLastName, long teacherId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
        this.teacherId=teacherId;
    }

    public long getId() {
        return id;
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

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }
}
