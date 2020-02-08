package main.java.com.epam.training.command.impl.admin;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.CommandType;
import main.java.com.epam.training.RedirectUrlCreator;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.CoursesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class AddCourseCommand implements Command {
    private static final String ADDED_RESULT = "&result=";
    private static final String TEACHER = "teacher";
    private static final String COURSE = "course";
    private static final String DESCRIPTION = "description";
    private static final String DATE_FROM = "dateFrom";
    private static final String DATE_TO = "dateTo";
    private CoursesService service;

    public AddCourseCommand(CoursesService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String teacher = request.getParameter(TEACHER);
        long teacherId = Long.parseLong(teacher);
        String name = request.getParameter(COURSE);
        String description = request.getParameter(DESCRIPTION);
        String dateFrom = request.getParameter(DATE_FROM);
        LocalDate localDateFrom = LocalDate.parse(dateFrom);
        String dateTo = request.getParameter(DATE_TO);
        LocalDate localDateTo = LocalDate.parse(dateTo);
        String result = service.addCourse(teacherId, name, description, localDateFrom, localDateTo);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_ADD_COURSE_PAGE)+ADDED_RESULT+result);
    }
}
