package com.assetbaitur.trello_app_react.repo;

import com.assetbaitur.trello_app_react.entities.CardTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CardTasksRepository extends JpaRepository<CardTasks, Long> {
    List<CardTasks> findAllByCardId(Long id);
}
