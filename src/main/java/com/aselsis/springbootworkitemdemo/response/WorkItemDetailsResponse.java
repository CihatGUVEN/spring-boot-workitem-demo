package com.aselsis.springbootworkitemdemo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class WorkItemDetailsResponse {

    private Long id;

    private  boolean resolved;

    private Date createdDate;

    private String title;

    private String description;

}
