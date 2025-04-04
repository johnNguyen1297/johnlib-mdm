package com.john.library.mdm.application.port.in.usecase;

import com.john.library.mdm.application.dto.response.Result;

/**
 * A base interface for use cases in the application.
 *
 * @param <T> the type of the input to the use case
 * @param <R> the type of the result of the use case
 */
public interface BaseUseCase<T, R> {

  /**
   * Executes the use case with the given input.
   *
   * @param input the input to the use case
   * @return the result of the use case
   */
  Result<R> execute(T input);
}
