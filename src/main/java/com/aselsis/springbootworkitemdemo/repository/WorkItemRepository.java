package com.aselsis.springbootworkitemdemo.repository;

import com.aselsis.springbootworkitemdemo.entity.WorkItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkItemRepository extends JpaRepository<WorkItem, Long> {
}
