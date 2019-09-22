package com.swagger2.entity.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {
    private Integer id;

    private String firstName;

    private Integer sex;

    private String subject;

    private Integer deptnum;

    private String comment;
}