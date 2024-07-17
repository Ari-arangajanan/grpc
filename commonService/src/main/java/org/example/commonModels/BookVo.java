package org.example.commonModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookVo implements Serializable {

    private Integer id;
    private String name;
    private Integer authorId;
    private String rollbackId;
}
