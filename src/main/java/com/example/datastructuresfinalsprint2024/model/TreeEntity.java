package com.example.datastructuresfinalsprint2024.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "tree_entity")
public class TreeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "tree_numbers", joinColumns = @JoinColumn(name = "tree_id"))
    @OrderColumn(name = "position") // Preserve order
    @Column(name = "number")
    private List<Integer> numbers = new ArrayList<>();

    @Column(name = "tree_structure")
    private String treeStructure;

    @Transient
    private TreeNode root;

    public TreeEntity() {}

    public Long getId() {
        return id;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
        this.root = buildBalancedTree(numbers);
        this.treeStructure = serializeTree(root);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
        this.treeStructure = serializeTree(root);
        this.numbers = extractNumbersFromTree(root);
    }

    public String getTreeStructure() {
        return treeStructure;
    }

    public void setTreeStructure(String treeStructure) {
        this.treeStructure = treeStructure;
        this.root = buildTreeFromJson(treeStructure);
        this.numbers = extractNumbersFromTree(root);
    }

    private TreeNode buildTreeFromJson(String treeJson) {
        if (treeJson == null || treeJson.isEmpty()) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(treeJson, TreeNode.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private TreeNode buildBalancedTree(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return null;
        }
        Collections.sort(numbers); // Ensure the list is sorted
        return buildBalancedTreeRecursive(numbers);
    }

    private TreeNode buildBalancedTreeRecursive(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return null;
        }
        int mid = numbers.size() / 2;
        TreeNode root = new TreeNode(numbers.get(mid));
        root.setLeft(buildBalancedTreeRecursive(numbers.subList(0, mid)));
        root.setRight(buildBalancedTreeRecursive(numbers.subList(mid + 1, numbers.size())));
        return root;
    }

    private String serializeTree(TreeNode node) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Integer> extractNumbersFromTree(TreeNode node) {
        List<Integer> numbers = new ArrayList<>();
        inOrderTraversal(node, numbers);
        return numbers;
    }

    private void inOrderTraversal(TreeNode node, List<Integer> numbers) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), numbers);
            numbers.add(node.getValue());
            inOrderTraversal(node.getRight(), numbers);
        }
    }
}
