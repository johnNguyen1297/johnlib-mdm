package com.john.library.mdm.application.port.in.mapper;

import com.john.library.mdm.application.port.in.usecase.BaseUpdateCommand;

/**
 * Base interface for application mappers.
 *
 * @param <D> the domain type
 * @param <C> the create command type
 * @param <U> the update command type, which extends {@link BaseUpdateCommand}
 */
public interface BaseApplicationMapper<D, C, U extends BaseUpdateCommand> {

  /**
   * Maps a create command to a domain object.
   *
   * @param createCommand the create command
   * @return the mapped domain object
   */
  D mapToDomain(C createCommand);

  /**
   * Maps an update command to a domain object.
   *
   * @param updateCommand the update command
   * @return the mapped domain object
   */
  D mapToDomain(U updateCommand);
}