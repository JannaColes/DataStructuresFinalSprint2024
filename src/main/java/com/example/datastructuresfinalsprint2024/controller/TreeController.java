package com.example.datastructuresfinalsprint2024.controller;

import com.example.datastructuresfinalsprint2024.model.TreeEntity;
import com.example.datastructuresfinalsprint2024.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TreeController {

    @Autowired
    private TreeService treeService;

    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    @ResponseBody
    public TreeEntity processNumbers(@RequestParam List<Integer> numbers) {
        return treeService.createTree(numbers);
    }

    @GetMapping("/previous-trees")
    public String getPreviousTrees(Model model) {
        List<TreeEntity> previousTrees = treeService.getPreviousTrees();
        model.addAttribute("previousTrees", previousTrees);
        return "previous-trees";
    }
}
