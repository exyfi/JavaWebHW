package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Event;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class TalkPage extends Page{

    private void talk(HttpServletRequest request, Map<String, Object> view) {
        String loginOrEmail = request.getParameter("login");
        String message = request.getParameter("message");



        try {
            getTalkService().validateEnter(getUser().getId(), loginOrEmail, message);
        } catch (ValidationException e) {
            view.put("loginOrEmail", loginOrEmail);
            view.put("message", message);
            view.put("error", e.getMessage());
            action(request,view);
            return ;
        }
        throw new RedirectException("/talk");




    }



    @Override
    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);

        if (getUser() == null) {
            throw new RedirectException("/index");
        }
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {

        view.put("talks", getTalkService().findAll(getUser().getId()));

    }
}

