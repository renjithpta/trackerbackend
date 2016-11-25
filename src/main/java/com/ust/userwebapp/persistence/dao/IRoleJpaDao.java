package com.ust.userwebapp.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ust.userwebapp.persistence.common.interfaces.IByNameApi;
import com.ust.userwebapp.persistence.model.Role;

public interface IRoleJpaDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role>,IByNameApi<Role> {
    //
}
