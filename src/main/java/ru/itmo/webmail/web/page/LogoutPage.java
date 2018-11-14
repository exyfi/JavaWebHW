package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Event;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.EventRepository;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.web.exception.RedirectException;
import ru.itmo.webmail.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class LogoutPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {

       // User user =request.getSession().getAttribute(USER_ID_SESSION_KEY);
        Long userId = (Long) request.getSession().getAttribute(USER_ID_SESSION_KEY);
        if (userId != null) {
            User user = getUserService().find(userId);
            Event event=new Event(Event.Type.LOGOUT);
            getEventService().make(user,event);

        }



        request.getSession().removeAttribute(USER_ID_SESSION_KEY);

        throw new RedirectException("/index");
    }
}
