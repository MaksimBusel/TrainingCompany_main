package main.java.com.epam.training.service;

import main.java.com.epam.training.dao.DaoHelper;
import main.java.com.epam.training.dao.DaoHelperFactory;
import main.java.com.epam.training.dao.StudentTaskDao;
import main.java.com.epam.training.dao.impl.StudentTaskDaoImpl;
import main.java.com.epam.training.entity.StudentTask;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.exception.ServiceException;

import javax.servlet.http.Part;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentTaskService {
    private static final String SAVE_DIRECTORY = "D:/java/Training company/src/main/students/tasks/";
    private static final String CONTENT_DISPOSITION = "content-disposition";
    private static final String FILENAME = "filename";

    private DaoHelperFactory daoHelperFactory;

    public StudentTaskService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<StudentTask> showMyMarks(long userId, long courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentTaskDao dao = helper.createStudentTaskDao();
            return dao.findMyMarks(userId, courseId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void estimateTask(long taskId, Integer mark, String feedback) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentTaskDao dao = helper.createStudentTaskDao();
            if (mark >= 0 && mark <= 10) {
                dao.updateMarkAndFeedbackById(taskId, mark, feedback);
            } else {
                throw new IllegalArgumentException(); //заменить на свое исключение
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<StudentTask> showStudentTask(long studentId, long courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentTaskDao dao = helper.createStudentTaskDao();
            return dao.findStudentTask(studentId, courseId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public String getStudentTask(long studentTaskId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentTaskDao dao = helper.createStudentTaskDao();
            Optional<StudentTask> studentTask = dao.findById(studentTaskId);
            if (studentTask.isPresent()) {
                String filePath = studentTask.get().getFilePath();
                return filePath;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    public void uploadStudentTask(Part studentTask, long taskId, long userId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentTaskDao dao = helper.createStudentTaskDao();
            String pathToFile = saveFile(studentTask);
            dao.updateFilePath(pathToFile, taskId, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void add(long taskId, long courseId, long userId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentTaskDao dao = helper.createStudentTaskDao();
            boolean resultCheck = dao.findByTaskIdUserId(userId, taskId);
            if (!resultCheck) {
                dao.addStudentTask(taskId, courseId, userId);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private String saveFile(Part studentTask) {
        String fileName = getFileName(studentTask);
        String pathToFile = SAVE_DIRECTORY + fileName;
        try (InputStream fileContent = studentTask.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(new File(pathToFile))) {
            int read = 0;
            final int bufferSize = 1024;
            final byte[] bytes = new byte[bufferSize];
            while ((read = fileContent.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();//логировать
        }
        return pathToFile;
    }

    private String getFileName(Part studentTask) {
        final String partHeader = studentTask.getHeader(CONTENT_DISPOSITION);
        for (String content : studentTask.getHeader(CONTENT_DISPOSITION).split(";")) {
            if (content.trim().startsWith(FILENAME)) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
