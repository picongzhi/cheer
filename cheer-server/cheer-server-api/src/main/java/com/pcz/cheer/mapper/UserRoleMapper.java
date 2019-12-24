package com.pcz.cheer.mapper;

import com.pcz.cheer.model.Role;
import com.pcz.cheer.model.UserRole;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * @author picongzhi
 */
@Component
public interface UserRoleMapper extends Mapper<UserRole>, MySqlMapper<UserRoleMapper> {
}
