package com.gnnu.dao;

import com.gnnu.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface StudentDao extends JpaSpecificationExecutor<Student>, JpaRepository<Student, Integer>, Serializable {
}
