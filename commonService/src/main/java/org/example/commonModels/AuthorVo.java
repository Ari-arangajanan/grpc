package org.example.commonModels;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorVo implements Serializable {
    private Integer id;
    private String name;
    private String rollbackId;


}
