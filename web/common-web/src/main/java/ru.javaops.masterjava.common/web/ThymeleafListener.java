package ru.javaops.masterjava.common.web;

import org.thymeleaf.TemplateEngine;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ThymeleafListener implements ServletContextListener {

    public static TemplateEngine engine;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        engine = ThymeleafUtil.getTemplateEngine(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
