package com.assetbaitur.trello_app_react.repo;

import com.assetbaitur.trello_app_react.entities.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CardRepository extends JpaRepository<Cards, Long> {
}
