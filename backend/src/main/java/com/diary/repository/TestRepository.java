package com.diary.repository;

import com.diary.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
}
