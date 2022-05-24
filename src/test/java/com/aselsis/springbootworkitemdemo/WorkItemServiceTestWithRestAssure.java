package com.aselsis.springbootworkitemdemo;

import com.aselsis.springbootworkitemdemo.dto.SimpleWorkItemDto;
import com.aselsis.springbootworkitemdemo.dto.WorkItemDto;
import com.aselsis.springbootworkitemdemo.entity.WorkItem;
import com.aselsis.springbootworkitemdemo.repository.WorkItemRepository;
import com.aselsis.springbootworkitemdemo.service.WorkItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkItemServiceTestWithRestAssure {

    @Mock
    private WorkItemRepository workItemRepository;

    @InjectMocks
    @Spy
    private WorkItemService workItemService;

    private WorkItem workItem;

    private WorkItemDto workItemDto;

    private SimpleWorkItemDto simpleWorkItemDto;

    @Before
    public void init(){

        workItem = WorkItem.builder()
                .id(1L)
                .createdDate(new Date())
                .description("description")
                .title("title")
                .build();

        workItemDto = WorkItemDto.builder()
                .id(workItem.getId())
                .createdDate(workItem.getCreatedDate())
                .description(workItem.getDescription())
                .title(workItem.getTitle())
                .build();

        simpleWorkItemDto = SimpleWorkItemDto.builder()
                .id(workItem.getId())
                .title(workItem.getTitle())
                .description(workItem.getDescription())
                .build();
    }

    @Test
    public void createWorkItem(){
        when(workItemRepository.save(any())).thenReturn(workItem);

        WorkItemDto returnworkItemDto = workItemService.createWorkItem(workItemDto);

        assertEquals(Optional.of(1L), Optional.ofNullable(returnworkItemDto.getId()));
        assertEquals("description", returnworkItemDto.getDescription());
    }

    @Test
    public void updateWorkItem(){
        when(workItemRepository.findById(any())).thenReturn(Optional.ofNullable(workItem));
        when(workItemRepository.save(any())).thenReturn(workItem);

        WorkItemDto returnworkItemDto = workItemService.updateWorkItem(workItemDto, 1L);

        assertEquals(Optional.of(1L), Optional.ofNullable(returnworkItemDto.getId()));
        assertEquals("description", returnworkItemDto.getDescription());
    }

    @Test
    public void deleteWorkItem(){
        workItemService.deleteWorkItem(1L);
        verify(workItemRepository).deleteById(1L);
    }

    @Test
    public void getAllWorkItems(){

        Pageable paging = PageRequest.of(0, 1);
        Page<WorkItem> workItemPage = new PageImpl<>(List.of(workItem), paging, 1);

        when(workItemRepository.findAll(isA(Pageable.class))).thenReturn(workItemPage);

        List<WorkItemDto> workItemDtoList = workItemService.getAllWorkItems(0,1);

        assertEquals(Optional.of(1L), Optional.ofNullable(workItemDtoList.get(0).getId()));
        assertEquals(Optional.of("title"), Optional.ofNullable(workItemDtoList.get(0).getTitle()));
    }

    @Test
    public void getAllWorkItemsByDetails(){

        Pageable paging = PageRequest.of(0, 1);
        Page<WorkItem> workItemPage = new PageImpl<>(List.of(workItem), paging, 1);

        when(workItemRepository.findAll(isA(Pageable.class))).thenReturn(workItemPage);

        List<WorkItemDto> workItemDtoList = workItemService.getAllWorkItems(0,1);

        assertEquals(Optional.of(1L), Optional.ofNullable(workItemDtoList.get(0).getId()));
        assertEquals(Optional.of("title"), Optional.ofNullable(workItemDtoList.get(0).getTitle()));
    }

    @Test
    public void getWorkItemById(){
        when(workItemRepository.findById(any())).thenReturn(Optional.ofNullable(workItem));

        WorkItemDto returnWorkItemDto = workItemService.getWorkItemById(1L);

        assertEquals(Optional.of(1L), Optional.ofNullable(returnWorkItemDto.getId()));
        assertEquals("description", returnWorkItemDto.getDescription());
    }

    @Test
    public void getWorkItemDetailsById(){
        when(workItemRepository.findById(any())).thenReturn(Optional.ofNullable(workItem));

        WorkItemDto returnWorkItemDto = workItemService.getWorkItemById(1L);

        assertEquals(Optional.of(1L), Optional.ofNullable(returnWorkItemDto.getId()));
        assertEquals("description", returnWorkItemDto.getDescription());
    }

}
