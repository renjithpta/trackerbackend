package com.ust.userwebapp.services.common;

import java.util.List;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.data.domain.Page;

import com.ust.userwebapp.persistence.common.interfaces.IByNameApi;
import com.ust.userwebapp.persistence.common.interfaces.IEntity;
import com.ust.userwebapp.persistence.common.interfaces.INameableEntity;
import com.ust.userwebapp.web.serach.ClientOperation;

public interface  CommonQuerySpecificationService<T extends INameableEntity> extends IByNameApi<T> {
	
	    List<T> searchAll(final String queryString);

	    List<T> searchPaginated(final String queryString, final int page, final int size);

	    Page<T> searchPaginated(final int page, final int size, final Triple<String, ClientOperation, String>... constraints);
	    
	    List<T> searchAll(final Triple<String, ClientOperation, String>... constraints);

	    T searchOne(final Triple<String, ClientOperation, String>... constraints);

}
