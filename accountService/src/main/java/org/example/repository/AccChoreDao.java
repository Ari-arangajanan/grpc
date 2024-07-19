package org.example.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.model.AccChore;
import org.example.model.Account;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccChoreDao extends BaseMapper<AccChore> {
    @Select("SELECT MAX(id) FROM AccChore")
    Long selectMaxId();

    @Update("ALTER TABLE AccChore AUTO_INCREMENT = #{autoIncrementValue}")
    void resetAutoIncrement(@Param("autoIncrementValue") Long autoIncrementValue);
}
