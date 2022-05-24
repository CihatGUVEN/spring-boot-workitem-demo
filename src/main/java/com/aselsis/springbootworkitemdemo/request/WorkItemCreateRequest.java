package com.aselsis.springbootworkitemdemo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkItemCreateRequest {

    private Long id;

    private  boolean resolved;

    private Date createdDate;

    private String title;

    private String description;
}
