package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.service.UserService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

public class ConfirmPage extends Page{



    private void action(HttpServletRequest request, Map<String, Object> view) {
         String secret = request.getParameter("secret");

         //ищем userId по сикрету и после этого делаем userId true
         long userId= getConfirmService().find(secret);
         getUserService().confirmUpdate(userId);
         throw new RedirectException("/index","successConfirm");






    }
    @Override
    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request,view);

    }


}
