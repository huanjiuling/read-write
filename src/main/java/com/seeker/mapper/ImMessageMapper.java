package com.seeker.mapper;

import com.seeker.entity.ImMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImMessageMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ImMessage record);

    int insertSelective(ImMessage record);

    ImMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImMessage record);

    int updateByPrimaryKey(ImMessage record);
}
