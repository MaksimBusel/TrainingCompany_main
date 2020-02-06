package main.java.com.epam.training.command.impl.student;

import main.java.com.epam.training.RedirectUrlCreator;
import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.CommandType;
import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.StudentTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddStudentTaskCommand implements Command {
    private StudentTaskService service;
    private static final String COURSE_ID = "&courseId=";

    public AddStudentTaskCommand(StudentTaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String task = request.getParameter("task_id");
        long taskId= Long.parseLong(task);
        String course = request.getParameter("course_id");
        long courseId= Long.parseLong(course);
        User user = (User) request.getSession().getAttribute("user");
        long userId= user.getId();
        service.add(taskId, courseId, userId);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MY_MARKS)+COURSE_ID+courseId);
    }
}
