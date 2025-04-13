package com.saipal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saipal.entity.Tranning;
@Repository
public interface TranningRepository extends JpaRepository<Tranning, Long> {
	Page<Tranning>findBy(Pageable pageable);

}
