package com.example.datastructuresfinalsprint2024.controller;


import com.example.datastructuresfinalsprint2024.model.TreeEntity;
import com.example.datastructuresfinalsprint2024.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;

@Controller
public class TreeController {

    @Autowired
    private TreeService treeService;

    private static final Logger logger = Logger.getLogger(TreeController.class.getName());

    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    @ResponseBody
    public TreeEntity processNumbers(@RequestParam String numbers) {
        List<Integer> numberList = Arrays.stream(numbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        logger.info("Received numbers: " + numberList);
        TreeEntity treeEntity = treeService.createTree(numberList);
        logger.info("Saved tree entity: " + treeEntity);
        return treeEntity;
    }

    @GetMapping("/previous-trees")
    public String getPreviousTrees(Model model) {
        List<TreeEntity> previousTrees = treeService.getPreviousTrees();
        model.addAttribute("previousTrees", previousTrees);
        return "previous-trees";
    }
}
