package com.aselsis.springbootworkitemdemo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class WorkItemResponse {

    private Long id;

    private Date date;

    private String title;

}
