package main.java.com.epam.training;

public class RedirectUrlCreator {
    private static final String REDIRECT_COMMAND = "{request.contextPath}controller?command=";

    public static String create(String command){
        return REDIRECT_COMMAND + command;
    }
}
