package com.example.fullstackbooktodospringboot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ics.springnuxt.dto.ZooDto;
import com.ics.springnuxt.model.Zoo;
import com.ics.springnuxt.repository.ZooRepository;
import com.ics.springnuxt.service.ZooService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ToDoServiceTest {

    @Autowired
    private ZooService zooService;

    @MockBean
    private ZooRepository zooRepository;

    @Test
    public void getToDosShouldReturnTodos() throws Exception {
        List<Zoo> todos = new ArrayList<>();
        Zoo todo = new Zoo();
        todo.setId(1L);
        todo.setAnimal("write unit tests");
        todo.setGender(false);
        todos.add(todo);
        when(zooRepository.findAll()).thenReturn(todos);
        List<ZooDto> todoDtoList = zooService.getAnimals();
        assertThat(todoDtoList).hasSize(1);
        assertEquals(1, todoDtoList.size());
    }
}
