package main.java.com.epam.training.command.impl.admin;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.CommandType;
import main.java.com.epam.training.RedirectUrlCreator;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class EditTaskCommand implements Command {
    private static final String COURSE_ID_PARAMETER="&course_id=";
    private TaskService service;

    public EditTaskCommand(TaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String task = request.getParameter("task_id");
        long taskId = Long.parseLong(task);
        String course = request.getParameter("course_id");
        long courseId = Long.parseLong(course);
        String name = request.getParameter("name");
        String dateFrom = request.getParameter("date_from");
        String dateTo = request.getParameter("date_to");
        LocalDate localDateFrom = LocalDate.parse(dateFrom);
        LocalDate localDateTo = LocalDate.parse(dateTo);
        service.editTask(courseId, name, localDateFrom, localDateTo, taskId);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MANAGE_TASKS_PAGE)+COURSE_ID_PARAMETER +courseId);
    }
}
