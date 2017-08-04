package org.kyu.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Question, Long> {

}
