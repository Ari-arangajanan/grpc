package org.example.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.model.Account;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountDao extends BaseMapper<Account> {
}
