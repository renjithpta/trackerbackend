package com.ust.userwebapp.services.usermanagement.impl;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ust.userwebapp.common.util.SearchUtilSec;
import com.ust.userwebapp.persistence.dao.IRoleJpaDao;
import com.ust.userwebapp.persistence.model.Principal;
import com.ust.userwebapp.persistence.model.Role;
import com.ust.userwebapp.services.common.AbstractCommonService;
import com.ust.userwebapp.services.usermanagement.IRoleService;
import com.ust.userwebapp.web.serach.ClientOperation;

@Service
@Transactional
public class RoleServiceImpl extends AbstractCommonService<Role> implements IRoleService{

    @Autowired
    IRoleJpaDao dao;

    public RoleServiceImpl() {
        super(Role.class);
    }

   

    @Override
    protected final IRoleJpaDao getDao() {
        return dao;
    }

    @Override
    public Specification<Role> resolveConstraint(final Triple<String, ClientOperation, String> constraint) {
        return SearchUtilSec.resolveConstraint(constraint, Role.class);
    }

    @Override
    protected JpaSpecificationExecutor<Role> getSpecificationExecutor() {
        return dao;
    }

}
