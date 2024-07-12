package com.imara.shipping.service.core;

import com.imara.shipping.model.core.AbstractObject;
import com.imara.shipping.repository.core.AbstractRepository;
import org.slf4j.Logger;

import java.util.*;
import javax.validation.*;

public abstract class AbstractService<T extends AbstractObject> {

  public T save(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }

    object.setDefaultValues();

    return (T) getRepository().save(object);
  }

  public T findById(Long var1) {
    return (T) getRepository().findById(var1).orElse(null);
  }

  public void saveAll(List<T> objects) {
    if (objects == null) return;
    getRepository().saveAll(objects);
  }

  public void deleteAll(List<T> objects) {
    if (objects == null) return;
    getRepository().deleteAll(objects);
  }

  public void deleteById(long id) {
    getRepository().deleteById(id);
  }

  public List<T> getAll() {
    return iterableToList(getRepository().findAll());
  }

  protected List<T> iterableToList(Iterable<T> source) {
    List<T> list = new ArrayList<>();
    source.forEach(x -> {
      list.add(x);
    });
    return list;
  }

  protected abstract AbstractRepository getRepository();

  protected abstract Logger getLog();

}