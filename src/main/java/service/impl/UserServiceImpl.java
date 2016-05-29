package service.impl;

import dao.UserMapper;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;


/**
 * Created by wenqing on 2016/5/29.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;

    @Override
    public User getUserById(int id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }
}
