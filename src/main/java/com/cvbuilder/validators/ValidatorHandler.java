package com.cvbuilder.validators;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ValidatorHandler {
  public ResponseEntity<Object> handleBindingResultErrors(BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      List<String> errors = bindingResult.getAllErrors().stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .collect(Collectors.toList());
      return ResponseEntity.badRequest().body(errors);
    }
    return null;

}
}
