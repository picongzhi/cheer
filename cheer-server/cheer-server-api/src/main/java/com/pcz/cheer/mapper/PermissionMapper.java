package com.pcz.cheer.mapper;

import com.pcz.cheer.model.Permission;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author picongzhi
 */
@Component
public interface PermissionMapper extends Mapper<Permission>, MySqlMapper<Permission> {
}
