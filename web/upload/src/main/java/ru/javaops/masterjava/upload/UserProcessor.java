package ru.javaops.masterjava.upload;

import ru.javaops.masterjava.model.User;
import ru.javaops.masterjava.model.UserFlag;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserProcessor {

    public List<User> process(final InputStream is) throws XMLStreamException {
        StaxStreamProcessor processor = new StaxStreamProcessor(is);
        List<User> users = new ArrayList<>();

        while (processor.doUntil(XMLEvent.START_ELEMENT, "User")) {
            final String email = processor.getAttribute("email");
            final String name = processor.getReader().getElementText();
            final UserFlag flag = UserFlag.valueOf(processor.getAttribute("flag"));
            final User user = new User(name, email, flag);
            users.add(user);
        }
        return users;
    }

}
