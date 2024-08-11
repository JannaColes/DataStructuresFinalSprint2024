package com.example.datastructuresfinalsprint2024.service;

import com.example.datastructuresfinalsprint2024.model.TreeEntity;
import com.example.datastructuresfinalsprint2024.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

@Service
public class TreeService {

    @Autowired
    private TreeRepository treeRepository;

    @Transactional
    public TreeEntity createTree(List<Integer> numbers) {
        TreeEntity tree = new TreeEntity();


        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        tree.setNumbers(new ArrayList<>(uniqueNumbers)); // Ensure mutable list


        treeRepository.save(tree);
        return tree;
    }

    public List<TreeEntity> getPreviousTrees() {
        return treeRepository.findAll();
    }
}

