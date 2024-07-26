package com.example.datastructuresfinalsprint2024.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tree_entity")
public class TreeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "tree_numbers", joinColumns = @JoinColumn(name = "tree_id"))
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
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public String getTreeStructure() {
        return treeStructure;
    }

    public void setTreeStructure(String treeStructure) {
        this.treeStructure = treeStructure;
    }

    public void insert(Integer value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, Integer value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }

        if (value <= root.getValue()) {  // Allow duplicates
            root.setLeft(insertRec(root.getLeft(), value));
        } else {
            root.setRight(insertRec(root.getRight(), value));
        }

        return root;
    }

    public void buildTreeFromJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.root = mapper.readValue(this.treeStructure, TreeNode.class);
    }

    public void buildJsonFromTree() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.treeStructure = mapper.writeValueAsString(this.root);
    }
}
