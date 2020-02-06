package main.java.com.epam.training.command.impl.admin;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.CommandType;
import main.java.com.epam.training.RedirectUrlCreator;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.CoursesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LockCourseCommand implements Command {
    private CoursesService service;
    public LockCourseCommand(CoursesService courseService) {
        this.service = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String course = request.getParameter("course_id");
        long courseId = Long.parseLong(course);
        service.lockCourse(courseId);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_EDIT_COURSE_PAGE));
    }
}
