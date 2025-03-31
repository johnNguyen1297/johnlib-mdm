package com.john.library.mdm.application.port.in.usecase.publisher;

import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.port.in.usecase.BaseUseCase;

public interface UpdatePublisherUseCase extends
                                        BaseUseCase<UpdatePublisherCommand, Result<Integer>> {

}
