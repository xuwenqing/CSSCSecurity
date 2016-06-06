package dao;

import model.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRole key);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    int deleteManyByUserId(Long userId);
    int deletManyByRoleId(Long roleId);

    int insertMany(@Param("user_id")Long user_id, @Param("role_ids")List<Long> role_ids);
    int deleteMany(@Param("user_id")Long user_id, @Param("role_ids")List<Long> role_ids);
}