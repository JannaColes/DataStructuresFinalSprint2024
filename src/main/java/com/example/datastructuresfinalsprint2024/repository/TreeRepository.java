package com.example.datastructuresfinalsprint2024.repository;

import com.example.datastructuresfinalsprint2024.model.TreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<TreeEntity, Long> {
}

