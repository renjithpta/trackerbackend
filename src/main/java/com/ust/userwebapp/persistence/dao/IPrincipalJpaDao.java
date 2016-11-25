package com.ust.userwebapp.persistence.dao;


import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ust.userwebapp.persistence.common.interfaces.IByNameApi;
import com.ust.userwebapp.persistence.model.Principal;

public interface IPrincipalJpaDao extends JpaRepository<Principal, Long>, JpaSpecificationExecutor<Principal>,IByNameApi<Principal> {
    //
}
