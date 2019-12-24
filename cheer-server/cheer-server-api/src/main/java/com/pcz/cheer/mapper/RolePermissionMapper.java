package com.pcz.cheer.mapper;

import com.pcz.cheer.model.RolePermission;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author picongzhi
 */
@Component
public interface RolePermissionMapper extends Mapper<RolePermission>, MySqlMapper<RolePermission> {
}
