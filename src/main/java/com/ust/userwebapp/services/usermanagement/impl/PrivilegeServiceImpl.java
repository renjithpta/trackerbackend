package com.ust.userwebapp.services.usermanagement.impl;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ust.userwebapp.common.util.SearchUtilSec;
import com.ust.userwebapp.persistence.dao.IPrivilegeJpaDao;
import com.ust.userwebapp.persistence.model.Principal;
import com.ust.userwebapp.persistence.model.Privilege;
import com.ust.userwebapp.services.common.AbstractCommonService;
import com.ust.userwebapp.services.usermanagement.IPrivilegeService;
import com.ust.userwebapp.web.serach.ClientOperation;

@Service
@Transactional
public class PrivilegeServiceImpl extends AbstractCommonService<Privilege> implements IPrivilegeService{

    @Autowired
    IPrivilegeJpaDao dao;

    public PrivilegeServiceImpl() {
        super(Privilege.class);
    }

  
   
    @Override
    protected final IPrivilegeJpaDao getDao() {
        return dao;
    }

    @Override
    public Specification<Privilege> resolveConstraint(final Triple<String, ClientOperation, String> constraint) {
        return SearchUtilSec.resolveConstraint(constraint, Privilege.class);
    }

    @Override
    protected JpaSpecificationExecutor<Privilege> getSpecificationExecutor() {
        return dao;
    }
}
