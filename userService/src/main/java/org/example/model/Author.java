package org.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("author_tbl")
@NoArgsConstructor
@AllArgsConstructor
public class Author implements Serializable {


    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField("rollbackId")
    private String rollbackId;


}
