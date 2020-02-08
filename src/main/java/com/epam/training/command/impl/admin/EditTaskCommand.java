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
    private static final String EDIT_RESULT = "&result=";
    private static final String COURSE_ID = "course_id";
    private static final String TASK_ID = "task_id";
    private static final String TASK_NAME = "name";
    private static final String DATE_FROM = "date_from";
    private static final String DATE_TO = "date_to";

    private TaskService service;

    public EditTaskCommand(TaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String task = request.getParameter(TASK_ID);
        long taskId = Long.parseLong(task);
        String course = request.getParameter(COURSE_ID);
        long courseId = Long.parseLong(course);
        String name = request.getParameter(TASK_NAME);
        String dateFrom = request.getParameter(DATE_FROM);
        String dateTo = request.getParameter(DATE_TO);
        LocalDate localDateFrom = LocalDate.parse(dateFrom);
        LocalDate localDateTo = LocalDate.parse(dateTo);
        String result = service.editTask(courseId, name, localDateFrom, localDateTo, taskId);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MANAGE_TASKS_PAGE)+COURSE_ID_PARAMETER+
                courseId+ EDIT_RESULT +result);
    }
}
