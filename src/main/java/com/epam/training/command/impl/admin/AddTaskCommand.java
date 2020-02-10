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
    private static final String ADDED_RESULT = "&result=";
    private static final String COURSE_ID = "course_id";
    private static final String TASK_NAME = "task_name";
    private static final String DATE_FROM = "date_from";
    private static final String DATE_TO = "date_to";

    private TaskService service;

    public AddTaskCommand(TaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String id = request.getParameter(COURSE_ID);
        long courseId = Long.parseLong(id);
        String name = request.getParameter(TASK_NAME);
        String dateFrom = request.getParameter(DATE_FROM);
        String dateTo = request.getParameter(DATE_TO);
        LocalDate localDateFrom = LocalDate.parse(dateFrom);
        LocalDate localDateTo = LocalDate.parse(dateTo);
        String result = service.addTask(courseId, name, localDateFrom, localDateTo);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MANAGE_TASKS_PAGE)+COURSE_ID_PARAMETER +
                courseId+ ADDED_RESULT +result);
    }
}
