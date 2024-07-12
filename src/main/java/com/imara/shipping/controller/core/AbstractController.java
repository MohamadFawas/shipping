package com.imara.shipping.controller.core;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import com.imara.shipping.dto.core.AbstractDTO;
import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.core.AbstractObject;
import com.imara.shipping.service.core.AbstractService;

import java.util.*;

public abstract class AbstractController<E extends AbstractObject, D> extends BaseController {

    public Result<List<D>> getAll() {
        AbstractDTOMapper mapper = getDTOMapper();
        if (mapper != null) return new Result<List<D>>(mapper.getDTOList(getService().getAll()));
        else return new Result<List<D>>(getService().getAll());
    }

    public Result<D> getByID(@RequestParam("id") long id) {
        AbstractDTOMapper mapper = getDTOMapper();
        if (mapper != null) return new Result<D>((D) mapper.getDTO(getService().findById(id)));
        else return new Result<D>((D) getService().findById(id));
    }

    public Result<D> save(@RequestBody D dto) {
        AbstractDTOMapper mapper = getDTOMapper();
        if (mapper != null) {
            E entity = (E) mapper.getEntity((AbstractDTO) dto);
            entity = (E) getService().save(entity);
            return new Result<D>((D) mapper.getDTO(entity));
        }
        else {
            return new Result<D>((D) getService().save((AbstractObject) dto));
        }
    }

    public Result deleteById(@RequestParam("id") long id) {
        getService().deleteById(id);
        return new Result();
    }

    protected abstract AbstractService getService();

    protected AbstractDTOMapper getDTOMapper() {
        return null;
    }

    protected abstract Logger getLog();

}
