package service.impl;

import dao.UserMapper;
import model.User;
import org.springframework.stereotype.Service;
import service.UserService;

import javax.annotation.Resource;

/**
 * Created by wenqing on 2016/5/29.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userDao;

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }
}
