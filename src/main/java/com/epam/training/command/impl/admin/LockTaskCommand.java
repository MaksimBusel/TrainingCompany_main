package main.java.com.epam.training.command.impl.admin;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.CommandType;
import main.java.com.epam.training.RedirectUrlCreator;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LockTaskCommand implements Command {
    private static final String COURSE_ID_PARAMETER = "&course_id=";
    private static final String LOCK_RESULT = "&result=";
    private static final String COURSE_ID = "course_id";
    private static final String TASK_ID = "task_id";

    private TaskService service;

    public LockTaskCommand(TaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String task = request.getParameter(TASK_ID);
        long taskId = Long.parseLong(task);
        String result = service.lockTask(taskId);
        String course = request.getParameter(COURSE_ID);
        long courseId = Long.parseLong(course);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MANAGE_TASKS_PAGE)+COURSE_ID_PARAMETER+
                courseId+ LOCK_RESULT +result);
    }
}
