package com.john.library.mdm.adapter.in.rest.controller;

import com.john.library.mdm.adapter.in.rest.api.AuthorApi;
import com.john.library.mdm.adapter.in.rest.dto.request.SaveAuthorRequest;
import com.john.library.mdm.adapter.in.rest.dto.response.base.BaseResponse;
import com.john.library.mdm.adapter.in.rest.handler.AuthorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public class AuthorController implements AuthorApi {

  private final AuthorHandler authorHandler;

  @Override
  public ResponseEntity<BaseResponse> createAuthor(SaveAuthorRequest request) {
    return new ResponseEntity<>(authorHandler.createAuthor(request), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> deleteAuthor(Integer id) {
    return ResponseEntity.ok(authorHandler.deleteAuthor(id));
  }

  @Override
  public ResponseEntity<BaseResponse> filterAuthors(String q, Pageable pageable) {
    return ResponseEntity.ok(authorHandler.filterAuthors(q, pageable));
  }

  @Override
  public ResponseEntity<BaseResponse> getAuthorDetails(Integer id) {
    return ResponseEntity.ok(authorHandler.getAuthorDetails(id));
  }

  @Override
  public ResponseEntity<BaseResponse> updateAuthor(Integer id,
                                                   SaveAuthorRequest saveAuthorRequest) {
    return ResponseEntity.ok(authorHandler.updateAuthor(id, saveAuthorRequest));
  }
}
