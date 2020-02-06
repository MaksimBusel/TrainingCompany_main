package main.java.com.epam.training.command.impl.student;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.CommandType;
import main.java.com.epam.training.RedirectUrlCreator;
import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.StudentCourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EnrollCourseCommand implements Command {
    private StudentCourseService service;

    public EnrollCourseCommand(StudentCourseService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        User user =(User) session.getAttribute("user");
        long userId = user.getId();
        String course = request.getParameter("course_id");
        long courseId= Long.parseLong(course);
        service.enroll(userId, courseId);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MY_COURSES));
    }
}
