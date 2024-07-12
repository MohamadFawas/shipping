package com.imara.shipping.dto.core;

import com.imara.shipping.model.core.AbstractObject;

import java.util.*;

public abstract class AbstractDTOMapper<E extends AbstractObject, D extends AbstractDTO> {

  public abstract E getEntity(D dto);

  public List<E> getEntityList(List<D> dtoList) {
    if (dtoList == null) return null;
    List<E> list = new ArrayList<>();
    Iterator<D> loop = dtoList.iterator();
    while (loop.hasNext()) {
      D dto = loop.next();
      list.add(getEntity(dto));
    }
    return list;
  }

  public D getDTO(E obj) {
    return getDTO(obj, AbstractDTO.DEFAULT);
  }

  public List<D> getDTOList(List<E> entList) {
    return getDTOList(entList, 0);
  }

  public List<D> getDTOList(List<E> entList, int format) {
    if (entList == null) return null;
    List<D> list = new ArrayList<>();
    Iterator<E> loop = entList.iterator();
    while (loop.hasNext()) {
      E entity = loop.next();
      list.add(getDTO(entity, format));
    }
    return list;
  }

  public abstract D getDTO(E obj, int format);

}
