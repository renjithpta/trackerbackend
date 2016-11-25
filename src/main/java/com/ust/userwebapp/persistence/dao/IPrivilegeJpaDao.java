package com.ust.userwebapp.persistence.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ust.userwebapp.persistence.common.interfaces.IByNameApi;
import com.ust.userwebapp.persistence.model.Privilege;

public interface IPrivilegeJpaDao extends JpaRepository<Privilege, Long>, JpaSpecificationExecutor<Privilege> ,IByNameApi<Privilege>{
    //
}
