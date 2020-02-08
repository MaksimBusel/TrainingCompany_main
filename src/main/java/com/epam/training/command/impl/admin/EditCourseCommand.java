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

public class EditCourseCommand implements Command {
    private static final String EDIT_RESULT = "&result=";
    private static final String COURSE_ID = "course_id";
    private static final String COURSE_NAME = "course_name";
    private static final String DESCRIPTION = "description";
    private static final String TEACHER = "teacher";
    private static final String DATE_FROM = "date_from";
    private static final String DATE_TO = "date_to";

    private CoursesService service;

    public EditCourseCommand(CoursesService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String course = request.getParameter(COURSE_ID);
        long courseId = Long.parseLong(course);
        String name = request.getParameter(COURSE_NAME);
        String description = request.getParameter(DESCRIPTION);
        String dateFrom = request.getParameter(DATE_FROM);
        String dateTo = request.getParameter(DATE_TO);
        LocalDate localDateFrom = LocalDate.parse(dateFrom);
        LocalDate localDateTo = LocalDate.parse(dateTo);
        String teacher = request.getParameter(TEACHER);
        long teacherId = Long.parseLong(teacher);
        String result = service.editCourse(courseId, teacherId, name, description, localDateFrom, localDateTo);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_EDIT_COURSE_PAGE)+ EDIT_RESULT +result);
    }
}
