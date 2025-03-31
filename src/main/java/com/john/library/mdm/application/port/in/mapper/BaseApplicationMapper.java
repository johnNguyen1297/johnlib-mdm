package com.john.library.mdm.application.port.in.mapper;

import com.john.library.mdm.application.port.in.usecase.BaseUpdateCommand;

public interface BaseApplicationMapper<T, C, U extends BaseUpdateCommand> {

  T mapToDomain(C createCommand);

  T mapToDomain(U updateCommand);
}
