package com.example.datastructuresfinalsprint2024.controller.service;

import com.example.datastructuresfinalsprint2024.model.TreeEntity;
import com.example.datastructuresfinalsprint2024.repository.TreeRepository;
import com.example.datastructuresfinalsprint2024.service.TreeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
public class TreeServiceTest {

    @Mock
    private TreeRepository treeRepository;

    @InjectMocks
    private TreeService treeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTree() {
        TreeEntity treeEntity = new TreeEntity();
        treeEntity.setNumbers(Arrays.asList(1, 2, 3));
        treeEntity.setTreeStructure("{\"value\":2,\"left\":{\"value\":1,\"left\":null,\"right\":null},\"right\":{\"value\":3,\"left\":null,\"right\":null}}");

        when(treeRepository.save(treeEntity)).thenReturn(treeEntity);

        TreeEntity result = treeService.createTree(Arrays.asList(1, 2, 3));

        System.out.println("Expected: " + "{\"value\":2,\"left\":{\"value\":1,\"left\":null,\"right\":null},\"right\":{\"value\":3,\"left\":null,\"right\":null}}");
        System.out.println("Actual: " + result.getTreeStructure());

        assertEquals("{\"value\":2,\"left\":{\"value\":1,\"left\":null,\"right\":null},\"right\":{\"value\":3,\"left\":null,\"right\":null}}", result.getTreeStructure());
    }

    @Test
    public void testGetPreviousTrees() {
        TreeEntity treeEntity = new TreeEntity();
        treeEntity.setNumbers(Arrays.asList(1, 2, 3));
        treeEntity.setTreeStructure("{\"value\":2,\"left\":{\"value\":1,\"left\":null,\"right\":null},\"right\":{\"value\":3,\"left\":null,\"right\":null}}");
        List<TreeEntity> trees = Arrays.asList(treeEntity);

        when(treeRepository.findAll()).thenReturn(trees);

        List<TreeEntity> result = treeService.getPreviousTrees();

        assertEquals(1, result.size());
        assertEquals(Arrays.asList(1, 2, 3), result.get(0).getNumbers());
        assertEquals("{\"value\":2,\"left\":{\"value\":1,\"left\":null,\"right\":null},\"right\":{\"value\":3,\"left\":null,\"right\":null}}", result.get(0).getTreeStructure());
    }
}

