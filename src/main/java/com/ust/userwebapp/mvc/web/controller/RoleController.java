package com.ust.userwebapp.mvc.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.ust.userwebapp.common.util.QueryConstants;
import com.ust.userwebapp.common.util.Um.Privileges;
import com.ust.userwebapp.persistence.model.Role;
import com.ust.userwebapp.services.usermanagement.IRoleService;
import com.ust.userwebapp.web.dto.RoleDto;
import com.ust.userwebapp.web.mvc.controller.common.AbstractController;
import com.ust.userwebapp.web.mvc.controller.common.ISortingController;
import com.ust.userwebapp.web.mvc.util.UmMappings;

@Controller
@RequestMapping(value = UmMappings.ROLES)
public class RoleController extends AbstractController<RoleDto, Role> implements ISortingController<Role> {

    @Autowired
    private IRoleService service;

    public RoleController() {
        super(RoleDto.class);
    }

    // API

    // search

    @RequestMapping(params = { QueryConstants.Q_PARAM }, method = RequestMethod.GET)
    @Secured(Privileges.CAN_ROLE_READ)
    @ResponseBody
     public List<Role> searchAll(@RequestParam(QueryConstants.Q_PARAM) final String queryString) {
        return searchAllInternal(queryString);
    }

    @RequestMapping(params = { QueryConstants.Q_PARAM, QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @Secured(Privileges.CAN_ROLE_READ)
    @ResponseBody
     public List<Role> searchAllPaginated(@RequestParam(QueryConstants.Q_PARAM) final String queryString, @RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        return searchAllPaginatedInternal(queryString, page, size);
    }
   
    @Override
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @Secured(Privileges.CAN_ROLE_READ)
    @ResponseBody
     public List<Role> findAllPaginatedAndSorted(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
            @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder, uriBuilder, response);
    }

    @Override
    @Secured(Privileges.CAN_ROLE_READ)
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    public List<Role> findAllPaginated(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findPaginatedAndSortedInternal(page, size, null, null, uriBuilder, response);
    }

    @Override
    @Secured(Privileges.CAN_ROLE_READ)
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<Role> findAllSorted(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }

    @Override
    @Secured(Privileges.CAN_ROLE_READ)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Role> findAll(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findAllInternal(request, uriBuilder, response);
    }

    // find - one
    @Secured(Privileges.CAN_ROLE_READ)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Role findOne(@PathVariable("id") final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findOneInternal(id, uriBuilder, response);
    }

    // create
    @Secured(Privileges.CAN_ROLE_WRITE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final Role resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        createInternal(resource, uriBuilder, response);
    }

    // update
    @Secured(Privileges.CAN_ROLE_WRITE)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody @Valid final Role resource) {
        updateInternal(id, resource);
    }

    // delete
    @Secured(Privileges.CAN_ROLE_WRITE)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
    }

    // Spring

    @Override
    protected final IRoleService getService() {
        return service;
    }

}
