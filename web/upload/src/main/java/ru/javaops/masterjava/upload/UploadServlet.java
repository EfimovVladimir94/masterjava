package ru.javaops.masterjava.upload;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.thymeleaf.context.WebContext;
import ru.javaops.masterjava.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static ru.javaops.masterjava.common.web.ThymeleafListener.engine;

@WebServlet("/")
public class UploadServlet extends HttpServlet {

    private final UserProcessor userProcessor = new UserProcessor();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final WebContext webContext = new WebContext(req, resp, req.getServletContext(), req.getLocale());
        engine.process("upload", webContext, resp.getWriter());
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final WebContext webContext = new WebContext(req, resp, req.getServletContext(), req.getLocale());
        Part part = req.getPart("fileToUpload");
        if (part.getSize() == 0) {
            throw new IllegalStateException("Upload file not been selected");
        }
        try (InputStream is = part.getInputStream()) {
            List<User> users = userProcessor.process(is);
            webContext.setVariable("users", users);
            engine.process("users", webContext, resp.getWriter());
        } catch (Exception e) {
            webContext.setVariable("exception", e);
        }

    }

}
