package org.example.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.practice.entity.Music;

@Mapper
public interface IMusicMapper extends BaseMapper<Music> {

}
