package com.ust.userwebapp.services.usermanagement.impl;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ust.userwebapp.common.util.SearchUtilSec;
import com.ust.userwebapp.persistence.dao.IPrincipalJpaDao;
import com.ust.userwebapp.persistence.model.Principal;
import com.ust.userwebapp.services.common.AbstractCommonService;
import com.ust.userwebapp.services.usermanagement.IPrincipalService;
import com.ust.userwebapp.web.serach.ClientOperation;

@Service
@Transactional
public class PrincipalServiceImpl extends AbstractCommonService<Principal> implements IPrincipalService {
	
	
	
	@Autowired
    IPrincipalJpaDao dao;

    public PrincipalServiceImpl() {
        super(Principal.class);
    }

	public Principal getCurrentPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PagingAndSortingRepository<Principal, Long> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

    
	 @Override
	    public Specification<Principal> resolveConstraint(final Triple<String, ClientOperation, String> constraint) {
	        return SearchUtilSec.resolveConstraint(constraint, Principal.class);
	    }

	    @Override
	    protected JpaSpecificationExecutor<Principal> getSpecificationExecutor() {
	        return dao;
	    }
	

}
