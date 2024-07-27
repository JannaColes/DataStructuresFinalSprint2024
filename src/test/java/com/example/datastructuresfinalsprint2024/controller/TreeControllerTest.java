package com.example.datastructuresfinalsprint2024.controller;

import com.example.datastructuresfinalsprint2024.model.TreeEntity;
import com.example.datastructuresfinalsprint2024.service.TreeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TreeController.class)
public class TreeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TreeService treeService;

    @Test
    public void testEnterNumbersPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/enter-numbers"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("enter-numbers"));
    }

    @Test
    void testProcessNumbers() throws Exception {

        TreeEntity treeEntity = new TreeEntity();
        treeEntity.setNumbers(Arrays.asList(1, 2, 3, 4, 5));
        when(treeService.createTree(anyList())).thenReturn(treeEntity);


        mockMvc.perform(post("/process-numbers")
                        .param("numbers", "1", "2", "3", "4", "5"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"numbers\":[1,2,3,4,5]}"));
    }

    private RequestBuilder get(String s) {
        return null;
    }

    @Test
    public void testGetPreviousTrees() throws Exception {
        List<TreeEntity> trees = Arrays.asList(new TreeEntity(), new TreeEntity());

        when(treeService.getPreviousTrees()).thenReturn(trees);

        mockMvc.perform(MockMvcRequestBuilders.get("/previous-trees"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("previous-trees"))
                .andExpect(MockMvcResultMatchers.model().attribute("previousTrees", trees));
    }
}
