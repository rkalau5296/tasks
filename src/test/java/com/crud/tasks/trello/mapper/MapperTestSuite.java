package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Autowired
    TaskMapper taskMapper;
    @Test
    public void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L, "TaskTitle", "TaskContent");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());

    }
    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "TaskDtoTitle", "TaskDtoContent");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getContent(), task.getContent());

    }
    @Test
    public void mapToTaskDtoListTest() {
        //Given
        List<TaskDto> taskDtoList  = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "TaskDtoTitle", "TaskDtoContent"));

        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "TaskTitle", "TaskContent"));

        //When
        List<TaskDto> newTaskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(taskList.get(0).getId(), newTaskDtoList.get(0).getId());
        assertEquals(taskList.get(0).getTitle(), newTaskDtoList.get(0).getTitle());
        assertEquals(taskList.get(0).getContent(), newTaskDtoList.get(0).getContent());
    }

    @Test
    public void mapToBoardsTest(){
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "ListDto", false));

        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "BoardDto", trelloListDtos));

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDto);

        //Then
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("BoardDto", trelloBoards.get(0).getName());
        assertEquals("1", trelloBoards.get(0).getLists().get(0).getId());
        assertEquals("ListDto", trelloBoards.get(0).getLists().get(0).getName());
        assertEquals(false, trelloBoards.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void mapToBoardsDtoTest(){
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "List", false));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "Board", trelloList));

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("Board", trelloBoards.get(0).getName());
        assertEquals("1", trelloBoards.get(0).getLists().get(0).getId());
        assertEquals("List", trelloBoards.get(0).getLists().get(0).getName());
        assertEquals(false, trelloBoards.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "ListDto", false));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //Then
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("ListDto", trelloLists.get(0).getName());
        assertEquals(false, trelloLists.get(0).isClosed());
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "List", false));

        //When
        List<TrelloListDto> trelloLists = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("List", trelloLists.get(0).getName());
        assertEquals(false, trelloLists.get(0).isClosed());
    }

    @Test
    public void mapToCardDtoTest(){
        //Given
        TrelloCard trelloCard = new TrelloCard("testCard", "testCardDesc", "testCardPos", "1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("testCard", trelloCardDto.getName());
        assertEquals("testCardDesc", trelloCardDto.getDescription());
        assertEquals("testCardPos", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }
    @Test
    public void mapToCardTest(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("testCard", "testCardDesc", "testCardPos", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("testCard", trelloCard.getName());
        assertEquals("testCardDesc", trelloCard.getDescription());
        assertEquals("testCardPos", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }
}
