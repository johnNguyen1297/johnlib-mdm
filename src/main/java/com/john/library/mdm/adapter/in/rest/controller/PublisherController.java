package com.john.library.mdm.adapter.in.rest.controller;

import com.john.library.mdm.adapter.in.rest.api.PublisherApi;
import com.john.library.mdm.adapter.in.rest.dto.request.SavePublisherRequest;
import com.john.library.mdm.adapter.in.rest.dto.response.BaseResponse;
import com.john.library.mdm.adapter.in.rest.handler.PublisherHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public class PublisherController implements PublisherApi {

  private final PublisherHandler publisherHandler;

  @Override
  public ResponseEntity<BaseResponse> createPublisher(SavePublisherRequest request) {
    return ResponseEntity.ok(publisherHandler.createPublisher(request));
  }

  @Override
  public ResponseEntity<Void> deletePublisher(Integer id) {
    return ResponseEntity.ok(publisherHandler.deletePublisher(id));
  }

  @Override
  public ResponseEntity<BaseResponse> filterPublishers(String q, String establishedYear,
                                                       Pageable pageable) {
    return ResponseEntity.ok(publisherHandler.filterPublishers(q, establishedYear, pageable));
  }

  @Override
  public ResponseEntity<BaseResponse> getPublisherDetails(Integer id) {
    return ResponseEntity.ok(publisherHandler.getPublisherDetails(id));
  }

  @Override
  public ResponseEntity<BaseResponse> updatePublisher(Integer id,
                                                      SavePublisherRequest savePublisherRequest) {
    return ResponseEntity.ok(publisherHandler.updatePublisher(id, savePublisherRequest));
  }
}
