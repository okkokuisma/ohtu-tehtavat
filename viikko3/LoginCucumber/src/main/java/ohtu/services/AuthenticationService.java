package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        Pattern un = Pattern.compile("[a-z]*");
        Matcher m = un.matcher(username);
        if (username.length() < 3 | !m.matches() | password.length() < 8) {
            return true;
        }

        boolean a = true;
        for (int i = 0; i < password.length(); i++) {
            if (!Character.isAlphabetic(password.charAt(i))) {
                a = false;
            }
        }

        return a;
    }
}
