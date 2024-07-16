package org.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("account_tbl")
public class Account {


    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer agentId;


}
