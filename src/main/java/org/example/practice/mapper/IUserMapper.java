package org.example.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.practice.entity.User;

@Mapper
public interface IUserMapper extends BaseMapper<User> {

}
