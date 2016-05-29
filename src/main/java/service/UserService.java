package service;

import model.User;

/**
 * Created by wenqing on 2016/5/29.
 */
public interface UserService {
    User getUserById(int id);
    int insert(User user);
}
