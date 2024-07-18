package org.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("account_tbl")
public class Capital implements Serializable {


    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer agentId;
    @TableField("rollbackId")
    private String rollbackId;


}
