package com.pcz.cheer.mapper;

import com.pcz.cheer.entity.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author picongzhi
 */
@Component
public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
}
