package main.java.com.epam.training.command.impl.admin;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.CommandType;
import main.java.com.epam.training.RedirectUrlCreator;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.StudentTaskService;
import main.java.com.epam.training.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class AddTaskCommand implements Command {
    private static final String COURSE_ID_PARAMETER="&course_id=";
    private static final String TEACHER = "teacher";
    private static final String COURSE = "course";
    private static final String DESCRIPTION = "description";
    private static final String DATE_FROM = "dateFrom";
    private static final String DATE_TO = "dateTo";
    
    private TaskService service;

    public AddTaskCommand(TaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String id = request.getParameter("course_id");
        long courseId = Long.parseLong(id);
        String name = request.getParameter("task_name");
        String dateFrom = request.getParameter("date_from");
        String dateTo = request.getParameter("date_to");
        LocalDate localDateFrom = LocalDate.parse(dateFrom);
        LocalDate localDateTo = LocalDate.parse(dateTo);
        service.addTask(courseId, name, localDateFrom, localDateTo);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MANAGE_TASKS_PAGE)+COURSE_ID_PARAMETER +courseId);
    }
}
