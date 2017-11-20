package com.c503.sc.dao;

import com.c503.sc.entity.DbEntity;
import com.c503.sc.entity.DbEntityKey;

public interface DbEntityMapper {
    int deleteByPrimaryKey(DbEntityKey key);

    int insert(DbEntity record);

    int insertSelective(DbEntity record);

    DbEntity selectByPrimaryKey(DbEntityKey key);

    int updateByPrimaryKeySelective(DbEntity record);

    int updateByPrimaryKey(DbEntity record);
}