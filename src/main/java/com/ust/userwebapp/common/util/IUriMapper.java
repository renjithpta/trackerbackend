package com.ust.userwebapp.common.util;
import com.ust.userwebapp.web.dto.IDto;

public interface IUriMapper {

    <T extends IDto> String getUriBase(final Class<T> clazz);

}
