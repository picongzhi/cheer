package com.pcz.cheer.mapper;

import com.pcz.cheer.model.User;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author picongzhi
 */
public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
}
