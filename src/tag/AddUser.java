package tag;

import bsu.rfe.java.group7.lab9.Truhov.varA1.entity.User;
import bsu.rfe.java.group7.lab9.Truhov.varA1.entity.UserList;
import bsu.rfe.java.group7.lab9.Truhov.varA1.helper.UserListHelper;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class AddUser extends SimpleTagSupport {

    private User user;
    public void setUser(User user) {
        this.user = user;
    }
    public void doTag() throws JspException, IOException {
        String errorMessage = null;
        UserList userList = (UserList) getJspContext().getAttribute("users", PageContext.APPLICATION_SCOPE);
        if (user.getLogin()==null || user.getLogin().equals("")) {
            errorMessage = "Логин не может быть пустым!";
        } else {
            if (user.getName()==null || user.getName().equals("")) {
                errorMessage = "Имя пользователя не может быть пустым!";
            }
        }
        if (errorMessage==null) {
            try {
                userList.addUser(user);
                UserListHelper.saveUserList(userList);
            } catch (UserList.UserExistsException e) {
                errorMessage = "Пользователь с таким логином уже существует!";
            }
        }

        getJspContext().setAttribute("errorMessage", errorMessage,
                PageContext.SESSION_SCOPE);
    }
}