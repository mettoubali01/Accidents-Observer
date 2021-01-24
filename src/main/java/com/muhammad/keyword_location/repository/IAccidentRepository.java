package com.muhammad.keyword_location.repository;

import com.muhammad.keyword_location.domain.Accident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccidentRepository extends JpaRepository<Accident, Long> {
}
