package ru.itmo.webmail.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class IndexPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void registrationDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have to confirm your registration");
    }
    private void incorrectSecretCode(HttpServletRequest request,Map<String,Object> view){
        view.put("message","Incorrect secret code, please try again");
    }
    private void successConfirm(HttpServletRequest request,Map<String,Object> view){
        view.put("message","Confirmation is success, you can log in");
    }
    private void confirmAfterEnter(HttpServletRequest request,Map<String,Object> view){
        view.put("message","Your account is not confirmed");
    }
}
