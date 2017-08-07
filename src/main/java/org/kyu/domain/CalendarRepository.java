package org.kyu.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long>{
	Long findFirstByOrderByIdDesc();
}
