package com.john.library.mdm.application.port.in.usecase.publisher;

import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.port.in.usecase.BaseUseCase;

public interface CreatePublisherUseCase extends
                                        BaseUseCase<CreatePublisherCommand, Result<Integer>> {

}
