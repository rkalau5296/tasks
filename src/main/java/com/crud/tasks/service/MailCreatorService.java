package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides conection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig);
        context.setVariable("goodbye_message", "Hope we'll meet you again. See you next time.");
        context.setVariable("company_details", "Adress: 1 Test street 1 12345 TestCity.");
        context.setVariable("preview_message", "This is some preview message.");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/create-trello-card-mail", context);
    }

    public String trelloCardQunatityEmail(String message) {
        List<String> functionalities = new ArrayList<>();
        functionalities.add("You can manage your tasks yourself");
        functionalities.add("Provides conection with Trello Account and others");
        functionalities.add("Application allows sending tasks to Trello. Yeah.");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig);
        context.setVariable("goodbye_message", "Bye, bye, bye. See you next time.");
        context.setVariable("company_details", "This is a new address to nowhere. Neverland 1 CA 0101 US.");
        context.setVariable("preview_message", "Preview message to noone.");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionalities);
        return templateEngine.process("mail/inform-quntity-trello-card-mail", context);
    }
}
