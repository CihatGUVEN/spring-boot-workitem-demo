package com.aselsis.springbootworkitemdemo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class WorkItemGetAllResponse {

    private Long id;

    private Date createdDate;

    private String title;

}
