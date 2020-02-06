package main.java.com.epam.training.command.impl.student;

import main.java.com.epam.training.RedirectUrlCreator;
import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.CommandType;
import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.StudentTaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

public class UploadStudentTaskCommand implements Command {
    private StudentTaskService service;
    private static final String COURSE_ID = "&courseId=";

    public UploadStudentTaskCommand(StudentTaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            Part studentTask = request.getPart("student_task");
            String task = request.getParameter("task_id");
            long taskId= Long.parseLong(task);
            HttpSession session = request.getSession();
            User student = (User) session.getAttribute("user");
            long userId = student.getId();
            service.uploadStudentTask(studentTask, taskId, userId);
        } catch (IOException | ServletException e) {
            throw new ServiceException(e);
        }
        String course = request.getParameter("course_id");
        long courseId = Long.parseLong(course);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MY_MARKS)+COURSE_ID+courseId);
    }
}
