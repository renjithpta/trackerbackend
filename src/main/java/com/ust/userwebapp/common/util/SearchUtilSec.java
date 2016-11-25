package com.ust.userwebapp.common.util;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.data.jpa.domain.Specification;

import com.ust.userweb.persistence.specification.QuerySpecification;
import com.ust.userwebapp.persistence.common.interfaces.IEntity;
import com.ust.userwebapp.web.serach.ClientOperation;

public class SearchUtilSec {
	

    private SearchUtilSec() {
        throw new UnsupportedOperationException();
    }

    // util

    public static <T extends IEntity> Specification<T> resolveConstraint(final Triple<String, ClientOperation, String> constraint, final Class<T> clazz) {
        final String constraintName = constraint.getLeft();
        final boolean negated = isConstraintNegated(constraint);

        if (constraintName.equals(QueryConstants.NAME)) {
            return QuerySpecification.getByNameSpecification(clazz, constraint.getMiddle(), constraint.getRight(), negated);
        }
        if (constraintName.equals(QueryConstants.ID)) {
            return QuerySpecification.getByIdSpecification(clazz, Long.parseLong(constraint.getRight()), negated);
        }
        return null;
    }

    static boolean isConstraintNegated(final Triple<String, ClientOperation, String> constraint) {
        return constraint.getMiddle().isNegated();
    }


}
