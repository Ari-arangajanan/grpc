package org.example.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.model.Author;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AuthorDao extends BaseMapper<Author> {
}
