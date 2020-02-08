package main.java.com.epam.training.dto;

import main.java.com.epam.training.entity.Identifable;

public class StudentTaskDto implements Identifable {
    private long id;
    private long userId;
    private long taskId;
    private String StudentFirstName;
    private String StudentLastName;
    private String taskName;
    private int mark;
    private String feedback;
    private String filePath;

    public StudentTaskDto(long studentTaskId, long userId, long taskId, String studentFirstName, String studentLastName,
                          String taskName, int mark, String feedback, String filePath) {
        this.id = studentTaskId;
        this.userId = userId;
        this.taskId = taskId;
        StudentFirstName = studentFirstName;
        StudentLastName = studentLastName;
        this.taskName = taskName;
        this.mark = mark;
        this.feedback = feedback;
        this.filePath=filePath;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getStudentFirstName() {
        return StudentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        StudentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return StudentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        StudentLastName = studentLastName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public long getId() {
        return id;
    }

}
