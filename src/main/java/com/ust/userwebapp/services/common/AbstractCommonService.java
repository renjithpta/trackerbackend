package com.ust.userwebapp.services.common;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.ust.userwebapp.common.util.SearchCommonUtill;
import com.ust.userwebapp.persistence.common.interfaces.IByNameApi;
import com.ust.userwebapp.persistence.common.interfaces.INameableEntity;
import com.ust.userwebapp.web.exception.UserWebAppBadRequestException;
import com.ust.userwebapp.web.exception.UserWebAppConflictException;
import com.ust.userwebapp.web.serach.ClientOperation;

@Transactional
public abstract  class AbstractCommonService<T extends INameableEntity> implements BaseService<T>{
	
	
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private Class<T> clazz;

    public AbstractCommonService(final Class<T> clazzToSet) {
        super();
        this.clazz = clazzToSet;
    }

   
    protected abstract PagingAndSortingRepository<T, Long> getDao();

    protected abstract JpaSpecificationExecutor<T> getSpecificationExecutor();

    @SuppressWarnings({ "unchecked" })
    public T findByName(String name){
       
       return  ( (IByNameApi<T>) getDao()).findByName(name);
    	
    }
    
    public List<T> searchAll(final String queryString) {
        Preconditions.checkNotNull(queryString);
        List<Triple<String, ClientOperation, String>> parsedQuery = null;
        try {
            parsedQuery = SearchCommonUtill.parseQueryString(queryString);
        } catch (final IllegalStateException illState) {
            logger.error("IllegalStateException on find operation");
            logger.warn("IllegalStateException on find operation", illState);
            throw new UserWebAppBadRequestException(illState);
        }

        final List<T> results = searchAll(parsedQuery.toArray(new ImmutableTriple[parsedQuery.size()]));
        return results;
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public List<T> searchPaginated(final String queryString, final int page, final int size) {
        List<Triple<String, ClientOperation, String>> parsedQuery = null;
        try {
            parsedQuery = SearchCommonUtill.parseQueryString(queryString);
        } catch (final IllegalStateException illState) {
            logger.error("IllegalStateException on find operation");
            logger.warn("IllegalStateException on find operation", illState);
            throw new  UserWebAppConflictException(illState);
        }

        final Page<T> resultPage = searchPaginated(page, size, parsedQuery.toArray(new ImmutableTriple[parsedQuery.size()]));
        return Lists.newArrayList(resultPage.getContent());
    }

    public List<T> searchAll(final Triple<String, ClientOperation, String>... constraints) {
        Preconditions.checkState(constraints != null);
        Preconditions.checkState(constraints.length > 0);
        final Specification<T> firstSpec = resolveConstraint(constraints[0]);
        Specifications<T> specifications = Specifications.where(firstSpec);
        for (int i = 1; i < constraints.length; i++) {
            specifications = specifications.and(resolveConstraint(constraints[i]));
        }
        if (firstSpec == null) {
            return Lists.newArrayList();
        }

        return getSpecificationExecutor().findAll(specifications);
    }

    @Override
    public T searchOne(final Triple<String, ClientOperation, String>... constraints) {
        Preconditions.checkState(constraints != null);
        Preconditions.checkState(constraints.length > 0);
        final Specification<T> firstSpec = resolveConstraint(constraints[0]);
        Specifications<T> specifications = Specifications.where(firstSpec);
        for (int i = 1; i < constraints.length; i++) {
            specifications = specifications.and(resolveConstraint(constraints[i]));
        }
        if (firstSpec == null) {
            return null;
        }

        return getSpecificationExecutor().findOne(specifications);
    }

    @Override
    public Page<T> searchPaginated(final int page, final int size, final Triple<String, ClientOperation, String>... constraints) {
        final Specification<T> firstSpec = resolveConstraint(constraints[0]);
        Preconditions.checkState(firstSpec != null);
        Specifications<T> specifications = Specifications.where(firstSpec);
        for (int i = 1; i < constraints.length; i++) {
            specifications = specifications.and(resolveConstraint(constraints[i]));
        }

        return getSpecificationExecutor().findAll(specifications, new PageRequest(page, size, null));
    }

    @SuppressWarnings({ "static-method", "unused" })
    public Specification<T> resolveConstraint(final Triple<String, ClientOperation, String> constraint) {
        throw new UnsupportedOperationException();
    } 

    // find - one

    @Transactional(readOnly = true)
    public T findOne(final long id) {
        return getDao().findOne(id);
    }

    // find - all


    @Transactional(readOnly = true)
    public List<T> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }


    @Transactional(readOnly = true)
    public Page<T> findAllPaginatedAndSortedRaw(final int page, final int size, final String sortBy, final String sortOrder) {
        final Sort sortInfo = constructSort(sortBy, sortOrder);
        return getDao().findAll(new PageRequest(page, size, sortInfo));
    }


    @Transactional(readOnly = true)
    public List<T> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder) {
        final Sort sortInfo = constructSort(sortBy, sortOrder);
        final List<T> content = getDao().findAll(new PageRequest(page, size, sortInfo)).getContent();
        if (content == null) {
            return Lists.newArrayList();
        }
        return content;
    }


    @Transactional(readOnly = true)
    public List<T> findAllPaginated(final int page, final int size) {
        final List<T> content = getDao().findAll(new PageRequest(page, size, null)).getContent();
        if (content == null) {
            return Lists.newArrayList();
        }
        return content;
    }


    @Transactional(readOnly = true)
    public List<T> findAllSorted(final String sortBy, final String sortOrder) {
        final Sort sortInfo = constructSort(sortBy, sortOrder);
        return Lists.newArrayList(getDao().findAll(sortInfo));
    }

    // save/create/persist

  
    public T create(final T entity) {
        Preconditions.checkNotNull(entity);
        final T persistedEntity = getDao().save(entity);
        return persistedEntity;
    }

    // update/merge

    public void update(final T entity) {
        Preconditions.checkNotNull(entity);
        getDao().save(entity);
       
    }

    // delete


    public void deleteAll() {
        getDao().deleteAll();
       
    }


    public void delete(final long id) {
        final T entity = getDao().findOne(id);
      
        getDao().delete(entity);
       
    }

    // count


    public long count() {
        return getDao().count();
    }


    protected final Sort constructSort(final String sortBy, final String sortOrder) {
        Sort sortInfo = null;
        if (sortBy != null) {
            sortInfo = new Sort(Direction.fromString(sortOrder), sortBy);
        }
        return sortInfo;
    }


	public Page<T> findAllPaginatedAndSortedRow(int page, int size, String sortBy, String sortOrder) {
		   final Sort sortInfo = constructSort(sortBy, sortOrder);
	        return getDao().findAll(new PageRequest(page, size, sortInfo));
	}

}
