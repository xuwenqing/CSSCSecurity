package service.impl;

import controller.dto.ResponsePackDto;
import dao.RegisterMapper;
import dao.UserMapper;
import dao.condition.RegisterCondition;
import model.Register;
import model.User;
import model.dto.DataPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.RegisterService;
import service.UserService;

import java.util.Date;
import java.util.List;

/**
 * Created by wenqing on 2016/6/2.
 */
@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper registerDao;
    @Autowired
    private UserService userService;

    @Override
    public ResponsePackDto register(Register register) {
        ResponsePackDto dto = new ResponsePackDto();
        if (!checkValid(register)) {
            dto.setStatus(400);
            dto.setError("用户名，密码，手机号不能为空");
            return dto;
        }

        User user = userService.findByUsername(register.getUsername());
        if(user != null) {
            dto.setStatus(400);
            dto.setError("用户名已存在");
            return dto;
        }
        Register old_register = registerDao.selectByName(register.getUsername());
        if(old_register != null) {
            dto.setStatus(400);
            dto.setError("用户名已经被注册");
            return dto;
        }
        register.setRegisterTime(new Date());
        if(registerDao.insert(register) != 1) {
            dto.setStatus(500);
            dto.setError("用户注册失败");
        }
        return dto;
    }

    @Override
    public ResponsePackDto getRegisters() {
        List<Register> registers = registerDao.selectAll();
        ResponsePackDto dto = new ResponsePackDto();
        dto.setData(registers);
        return dto;
    }

    @Override
    public DataPageDto getRegisters(RegisterCondition condition) {
        DataPageDto dto = new DataPageDto();
        List<Register> registers = registerDao.selectByRegisterCondition(condition);
        long count = registerDao.selectConditionCount(condition);
        dto.setData(registers);
        dto.setRecordsTotal(count);
        return dto;
    }

    @Override
    public ResponsePackDto active(int register_id) {
        ResponsePackDto dto = new ResponsePackDto();
        Register register = registerDao.selectByPrimaryKey(register_id);
        if(register == null) {
            dto.setStatus(400);
            dto.setError("激活用户不存在");
            return dto;
        }
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(register.getPassword());
        user.setPhone(register.getPhone());
        if(userService.createUser(user) == null) {
            dto.setStatus(500);
            dto.setError("用户激活失败");
        }
        registerDao.deleteByPrimaryKey(register_id);
        return dto;
    }

    private boolean checkValid(Register register) {
        String password = register.getPassword() != null ? register.getPassword().trim() : null;
        String phone = register.getPhone() != null ? register.getPhone().trim() : null;
        String username = register.getUsername() != null ? register.getUsername() : null;
        if(password == null || password.equals("")) {
            return false;
        }
        else if(phone == null || phone.equals("")) {
            return false;
        }
        else if(username == null || username.equals("")) {
            return false;
        }
        return true;
    }
}
