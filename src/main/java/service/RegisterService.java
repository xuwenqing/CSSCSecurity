package service;

import dao.condition.RegisterCondition;
import model.dto.DataPageDto;
import controller.dto.ResponsePackDto;
import model.Register;

/**
 * Created by wenqing on 2016/6/2.
 */
public interface RegisterService {
    ResponsePackDto register(Register register);
    ResponsePackDto getRegisters();
    DataPageDto getRegisters(RegisterCondition condition);
    ResponsePackDto active(int register_id);
}
