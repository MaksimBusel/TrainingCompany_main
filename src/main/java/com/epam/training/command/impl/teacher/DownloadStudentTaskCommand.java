package main.java.com.epam.training.command.impl.teacher;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.StudentTaskService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadStudentTaskCommand implements Command {
    private StudentTaskService service;

    public DownloadStudentTaskCommand(StudentTaskService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String studentTask = request.getParameter("student_task_id");
        long studentTaskId = Long.parseLong(studentTask);
        String pathToFile = service.getStudentTask(studentTaskId);

        File file = new File(pathToFile);
        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(pathToFile);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int) file.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
        response.setHeader(headerKey, headerValue);
        downloadFile(file, response);

        return null;
    }

    private void downloadFile(File file, HttpServletResponse response) {
        try (FileInputStream inStream = new FileInputStream(file); OutputStream outStream = response.getOutputStream()) {
            final int bufferSize = 4 * 1024;
            byte[] buffer = new byte[bufferSize];
            int bytesRead;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();// my exception
        }
    }
}
