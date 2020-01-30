package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskMapper taskMapper;
    @MockBean
    private DbService service;

    @Test
    public void shouldFetchTasksList() throws Exception {
        //Given
        List<TaskDto> listTasksDto = new ArrayList<>();
        listTasksDto.add(new TaskDto(1L, "Task", "First task desc"));
        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(listTasksDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Task")))
                .andExpect(jsonPath("$[0].content", is("First task desc")));
        verify(taskMapper, times(1)).mapToTaskDtoList(service.getAllTasks());
    }

    @Test
    public void shouldFetchOneTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task", "First task desc");

        when(taskMapper.mapToTaskDto(service.getTaskById(1L))).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task")))
                .andExpect(jsonPath("$.content", is("First task desc")));
        verify(taskMapper, times(1)).mapToTaskDto(service.getTaskById(1L));
    }

    @Test
    public void shouldPostTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task", "First task desc");
        Task task = new Task(1L, "Name", "Desc");
        when(service.saveTask(taskMapper.mapToTask(taskDto))).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        // When&Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Name")))
                .andExpect(jsonPath("$.content", is("Desc")));
        verify(service, times(1)).saveTask(taskMapper.mapToTask(taskDto));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskUpdated = new TaskDto(1L, "NameUpdated", "DescUpdated");
        when(taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskUpdated)))).thenReturn(taskUpdated);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskUpdated);

        // When&Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("NameUpdated")))
                .andExpect(jsonPath("$.content", is("DescUpdated")));
        verify(taskMapper, times(1)).mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskUpdated)));
    }
    @Test
    public void shouldDeleteTask() throws Exception {
        //Given & When & Then
        List<TaskDto> listTasksDto = new ArrayList<>();
        listTasksDto.add(new TaskDto(1L, "Task", "First task desc"));

        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service,  times(1)).deleteById(1L);
    }

}
