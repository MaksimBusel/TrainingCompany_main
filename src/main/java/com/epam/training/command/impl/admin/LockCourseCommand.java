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
    private static final String COURSE_ID = "course_id";
    private static final String LOCK_RESULT = "&result=";

    private CoursesService service;

    public LockCourseCommand(CoursesService courseService) {
        this.service = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String course = request.getParameter(COURSE_ID);
        long courseId = Long.parseLong(course);
        String result = service.lockCourse(courseId);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_EDIT_COURSE_PAGE)+ LOCK_RESULT +result);
    }
}
