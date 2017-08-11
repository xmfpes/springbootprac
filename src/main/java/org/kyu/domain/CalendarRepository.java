package org.kyu.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long>{
	Long findFirstByOrderByIdDesc();
	List<Calendar> findByWriterId(Long writerId);
}
