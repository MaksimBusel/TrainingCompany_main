package main.java.com.epam.training.command.impl.teacher;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.CommandType;
import main.java.com.epam.training.RedirectUrlCreator;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.StudentTaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EstimateTaskCommand implements Command {
    private static final String TASK_ID_PARAMETER = "&task_id=";
    private static final String MARK = "mark";
    private static final String TASK_ID = "task_id";
    private static final String STUDENT_TASK_ID = "student_task_id";
    private static final String FEEDBACK = "feedback";

    private StudentTaskService service;

    public EstimateTaskCommand(StudentTaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String mark = request.getParameter(MARK);
        Integer studentMark = Integer.valueOf(mark);
        String feedback = request.getParameter(FEEDBACK);
        String studentTask =  request.getParameter(STUDENT_TASK_ID);
        long studentTaskId= Long.parseLong(studentTask);
        service.estimateTask(studentTaskId, studentMark, feedback);
        String taskId =  request.getParameter(TASK_ID);

        return CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_STUDENTS_HAVE_TASK)+TASK_ID_PARAMETER+taskId);
    }
}
