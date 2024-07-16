package org.example.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.model.Book;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BookDao extends BaseMapper<Book> {
}
