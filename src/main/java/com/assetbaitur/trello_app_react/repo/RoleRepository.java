package com.assetbaitur.trello_app_react.repo;

import com.assetbaitur.trello_app_react.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
}
