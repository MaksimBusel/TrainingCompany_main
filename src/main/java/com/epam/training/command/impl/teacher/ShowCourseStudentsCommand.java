package main.java.com.epam.training.command.impl.teacher;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.Pages;
import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCourseStudentsCommand implements Command {
    private UserService service;

    public ShowCourseStudentsCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String course = request.getParameter("course_id");
        long courseId= Long.parseLong(course);
        List<User> students = service.showCourseStudents(courseId);
        request.setAttribute("students", students);
        request.setAttribute("course_id", courseId);

        return CommandResult.forward(Pages.COURSE_STUDENTS);
    }
}
