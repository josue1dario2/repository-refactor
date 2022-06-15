package com.proyect.tinder.repository;

import com.proyect.tinder.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Integer> {

    List<Vote> findByPet1OrderByDateDesc(Integer id);

    List<Vote> findByPet2OrderByDateDesc(Integer id);
}
