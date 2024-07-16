package org.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("book_tbl")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer authorId;
}
