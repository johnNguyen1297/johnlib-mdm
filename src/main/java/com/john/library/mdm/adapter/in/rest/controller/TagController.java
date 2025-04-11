package com.john.library.mdm.adapter.in.rest.controller;

import com.john.library.mdm.adapter.in.rest.api.TagApi;
import com.john.library.mdm.adapter.in.rest.dto.request.SaveTagRequest;
import com.john.library.mdm.adapter.in.rest.dto.response.base.BaseResponse;
import com.john.library.mdm.adapter.in.rest.handler.TagHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public class TagController implements TagApi {

  private final TagHandler tagHandler;

  @Override
  public ResponseEntity<BaseResponse> createTag(final SaveTagRequest saveTagRequest) {
    val response = tagHandler.createTag(saveTagRequest);
    return new ResponseEntity<>(response, response.getStatus());
  }

  @Override
  public ResponseEntity<BaseResponse> deleteTag(final Integer id) {
    return ResponseEntity.ok(tagHandler.deleteTag(id));
  }

  @Override
  public ResponseEntity<BaseResponse> filterTags(final String q, final Pageable pageable) {
    return ResponseEntity.ok(tagHandler.filterTags(q, pageable));
  }

  @Override
  public ResponseEntity<BaseResponse> getTagDetails(final Integer id) {
    return ResponseEntity.ok(tagHandler.getTagDetails(id));
  }

  @Override
  public ResponseEntity<BaseResponse> updateTag(final Integer id,
                                                final SaveTagRequest saveTagRequest) {
    return ResponseEntity.ok(tagHandler.updateTag(id, saveTagRequest));
  }
}
