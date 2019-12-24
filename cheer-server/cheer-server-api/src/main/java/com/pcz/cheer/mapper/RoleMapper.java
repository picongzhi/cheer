package com.pcz.cheer.mapper;

import com.pcz.cheer.model.Role;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author picongzhi
 */
@Component
public interface RoleMapper extends Mapper<Role>, MySqlMapper<Role> {
}
