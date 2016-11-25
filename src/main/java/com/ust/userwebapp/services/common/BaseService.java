package com.ust.userwebapp.services.common;

import com.ust.userwebapp.persistence.common.interfaces.IEntity;
import com.ust.userwebapp.persistence.common.interfaces.INameableEntity;

public interface BaseService<T extends INameableEntity> extends CommonService<T>,CommonQuerySpecificationService<T>{

}
