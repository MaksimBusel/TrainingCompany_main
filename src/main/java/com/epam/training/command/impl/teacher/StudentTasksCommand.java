package main.java.com.epam.training.command.impl.teacher;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.Pages;
import main.java.com.epam.training.entity.StudentTask;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.StudentTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StudentTasksCommand implements Command {
    private static final String COURSE_ID = "course_id";
    private static final String STUDENT_ID = "student_id";
    private static final String STUDENT_NAME = "name";

    private StudentTaskService service;

    public StudentTasksCommand(StudentTaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String student = request.getParameter(STUDENT_ID);
        long studentId= Long.parseLong(student);
        String course = request.getParameter(COURSE_ID);
        long courseId= Long.parseLong(course);
        String studentName = request.getParameter(STUDENT_NAME);
        List<StudentTask> tasks = service.showStudentTask(studentId, courseId);
        request.setAttribute("tasks", tasks);
        request.setAttribute("student_name", studentName);

        return CommandResult.forward(Pages.STUDENT_TASKS);
    }
}
