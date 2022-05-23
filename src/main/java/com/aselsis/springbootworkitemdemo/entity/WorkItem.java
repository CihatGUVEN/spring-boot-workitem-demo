package com.aselsis.springbootworkitemdemo.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workitem", schema="public")
public class WorkItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
    private  boolean resolved;

    private Date date;

    private String title;

    private String description;

}
