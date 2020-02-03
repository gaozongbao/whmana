package com.cmdi.dao;

import org.apache.ibatis.annotations.Param;

import com.cmdi.model.GroupInfo;

public interface GroupInfoDao {
    int deleteByPrimaryKey(String id);

    int insert(GroupInfo record);

    int insertSelective(GroupInfo record);

    GroupInfo selectByPrimaryKey(@Param("id") String id);

    int updateByPrimaryKeySelective(GroupInfo record);

    int updateByPrimaryKey(GroupInfo record);
}