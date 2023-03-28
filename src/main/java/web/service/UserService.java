package web.service;

import web.model.User;
import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public  void saveUser(User user);

    public User getUser(int id);

    public void deleteUser(int id);
}
