package com.saipal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saipal.entity.PersonType;
@Repository
public interface PersonTypeRepository extends JpaRepository<PersonType, Long> {


}
