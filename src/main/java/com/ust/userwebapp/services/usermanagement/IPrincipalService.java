package com.ust.userwebapp.services.usermanagement;
import com.ust.userwebapp.persistence.common.interfaces.INameableEntity;
import com.ust.userwebapp.persistence.model.Principal;
import com.ust.userwebapp.services.common.BaseService;
import com.ust.userwebapp.services.common.CommonQuerySpecificationService;
import com.ust.userwebapp.services.common.CommonService;

public interface IPrincipalService extends BaseService<Principal> {

    Principal getCurrentPrincipal();

}
