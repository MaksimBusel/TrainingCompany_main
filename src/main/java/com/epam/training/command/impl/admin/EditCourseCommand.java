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
    private CoursesService service;

    public EditCourseCommand(CoursesService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String course = request.getParameter("course_id");
        long courseId = Long.parseLong(course);
        String name = request.getParameter("course_name");
        String description = request.getParameter("description");
        String dateFrom = request.getParameter("date_from");
        String dateTo = request.getParameter("date_to");
        LocalDate localDateFrom = LocalDate.parse(dateFrom);
        LocalDate localDateTo = LocalDate.parse(dateTo);
        String teacher = request.getParameter("teacher");
        long teacherId = Long.parseLong(teacher);
        service.editCourse(courseId, teacherId, name, description, localDateFrom, localDateTo);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_EDIT_COURSE_PAGE));
    }
}
