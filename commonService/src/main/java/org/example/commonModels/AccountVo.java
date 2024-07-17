package org.example.commonModels;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountVo implements Serializable {
    private Integer id;
    private String name;
    private Integer agentId;
    private String rollbackId;
}
