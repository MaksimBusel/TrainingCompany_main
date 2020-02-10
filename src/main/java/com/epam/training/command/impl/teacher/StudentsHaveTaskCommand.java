package main.java.com.epam.training.command.impl.teacher;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.Pages;
import main.java.com.epam.training.dto.StudentTaskDto;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.StudentTaskDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StudentsHaveTaskCommand implements Command {
    private static final String TASK_ID = "task_id";
    private static final String TASK_NAME = "task_name";

    private StudentTaskDtoService service;

    public StudentsHaveTaskCommand(StudentTaskDtoService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String task = request.getParameter(TASK_ID);
        long taskId= Long.parseLong(task);
        List<StudentTaskDto> students = service.showStudentsHaveTask(taskId);
        String taskName = request.getParameter(TASK_NAME);
        request.setAttribute("students", students);
        request.setAttribute("taskName",taskName);

        return CommandResult.forward(Pages.STUDENTS_HAVE_TASKS);
    }
}
