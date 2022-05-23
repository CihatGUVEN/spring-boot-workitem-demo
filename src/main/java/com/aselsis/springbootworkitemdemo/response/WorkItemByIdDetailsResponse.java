package com.aselsis.springbootworkitemdemo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class WorkItemByIdDetailsResponse {

    private Long id;

    private  boolean resolved;

    private Date date;

    private String title;

    private String description;
}
