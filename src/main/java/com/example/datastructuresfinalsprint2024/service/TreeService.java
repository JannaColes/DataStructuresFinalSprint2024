package com.example.datastructuresfinalsprint2024.service;

import com.example.datastructuresfinalsprint2024.model.TreeEntity;
import com.example.datastructuresfinalsprint2024.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeService {

    @Autowired
    private TreeRepository treeRepository;

    public TreeEntity createTree(List<Integer> numbers) {
        TreeEntity tree = new TreeEntity();
        tree.setNumbers(numbers);
        treeRepository.save(tree);
        return tree;
    }

    public List<TreeEntity> getPreviousTrees() {
        return treeRepository.findAll();
    }
}
