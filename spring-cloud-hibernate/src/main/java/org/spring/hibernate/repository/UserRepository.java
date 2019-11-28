package org.spring.hibernate.repository;

import org.spring.hibernate.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends JpaRepository<UserPO, Serializable>, JpaSpecificationExecutor<UserPO> {
}
