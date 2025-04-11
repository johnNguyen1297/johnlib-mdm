package com.john.library.mdm.adapter.in.rest.handler;

import com.john.library.mdm.adapter.in.rest.dto.request.SavePublisherRequest;
import com.john.library.mdm.adapter.in.rest.dto.response.base.BaseResponse;
import com.john.library.mdm.adapter.in.rest.mapper.PublisherRestMapper;
import com.john.library.mdm.application.dto.request.AppPageRequest;
import com.john.library.mdm.application.port.in.usecase.publisher.CreatePublisherCommand;
import com.john.library.mdm.application.port.in.usecase.publisher.CreatePublisherUseCase;
import com.john.library.mdm.application.port.in.usecase.publisher.DeletePublisherUseCase;
import com.john.library.mdm.application.port.in.usecase.publisher.FilterPublisherQuery;
import com.john.library.mdm.application.port.in.usecase.publisher.FilterPublisherUseCase;
import com.john.library.mdm.application.port.in.usecase.publisher.GetPublisherDetailUseCase;
import com.john.library.mdm.application.port.in.usecase.publisher.UpdatePublisherCommand;
import com.john.library.mdm.application.port.in.usecase.publisher.UpdatePublisherUseCase;
import java.time.Year;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("rawtypes")
public class PublisherHandler {

  private final CreatePublisherUseCase createPublisherUseCase;
  private final UpdatePublisherUseCase updatePublisherUseCase;
  private final FilterPublisherUseCase filterPublisherUseCase;
  private final DeletePublisherUseCase deletePublisherUseCase;
  private final GetPublisherDetailUseCase getPublisherDetailUseCase;

  private final PublisherRestMapper restInboundMapper;

  public BaseResponse createPublisher(SavePublisherRequest request) {
    CreatePublisherCommand command = restInboundMapper.mapToCommand(request);
    return BaseResponse.of(createPublisherUseCase.execute(command));
  }

  public BaseResponse deletePublisher(Integer id) {
    return BaseResponse.of(deletePublisherUseCase.execute(id));
  }

  public BaseResponse filterPublishers(String q, String establishedYear, Pageable pageable) {
    val query = FilterPublisherQuery.builder()
                                    .q(q)
                                    .establishedYear(
                                        establishedYear != null ? Year.parse(establishedYear)
                                                                : null)
                                    .pageRequest(AppPageRequest.of(pageable))
                                    .build();

    return BaseResponse.of(filterPublisherUseCase.execute(query));
  }

  public BaseResponse getPublisherDetails(Integer id) {
    return BaseResponse.of(getPublisherDetailUseCase.execute(id));
  }

  public BaseResponse updatePublisher(Integer id, SavePublisherRequest request) {
    UpdatePublisherCommand command = restInboundMapper.mapToCommand(id, request);
    return BaseResponse.of(updatePublisherUseCase.execute(command));
  }
}
