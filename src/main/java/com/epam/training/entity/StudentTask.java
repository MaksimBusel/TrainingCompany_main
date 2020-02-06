package main.java.com.epam.training.entity;

import main.java.com.epam.training.entity.Identifable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class StudentTask implements Identifable, Serializable {
    private Task task;
    private long studentTaskId;
    private Integer mark;
    private String feedback;
    private String filePath;

    public StudentTask(long taskId, long studentTaskId, String name, String dateFrom, String dateTo, Integer mark,
                       String feedback, String filePath) {
        task = new Task(taskId, name, dateFrom, dateTo);
        this.filePath = filePath;
        this.studentTaskId=studentTaskId;
        this.mark = mark;
        this.feedback = feedback;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public LocalDate getDateFrom() {
        return task.getDateFrom();
    }

    public LocalDate getDateTo() {
        return task.getDateTo();
    }

    public String getName() {
        return task.getName();
    }

    public long getStudentTaskId() {
        return studentTaskId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public long getId() {
        return task.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        StudentTask that = (StudentTask) o;
        return task !=null? task.equals(that.task): that.task == null &&
               mark != null ? mark.equals(that.mark) : that.mark == null &&
               feedback != null ? feedback.equals(that.feedback) : that.feedback == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, mark, feedback);
    }

    @Override
    public String toString() {
        return task.toString() + "mark=" + mark + ", feedback='" + feedback  + ", filePath="+filePath+'}';
    }
}
