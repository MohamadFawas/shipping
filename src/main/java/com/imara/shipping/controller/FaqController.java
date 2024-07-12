package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.FaqDTO;
import com.imara.shipping.dto.FaqDTOMapper;
import com.imara.shipping.model.Faq;
import com.imara.shipping.service.FaqService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faq/")
@Slf4j
@Transactional
public class FaqController extends AbstractController<Faq, FaqDTO> {

    @Autowired
    FaqService faqService;
    @Autowired
    FaqDTOMapper faqDTOMapper;
    @Override
    protected FaqService getService() {
        return faqService;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    protected FaqDTOMapper getDTOMapper() {
        return faqDTOMapper;
    }



    @PostMapping("save")
    public Result<FaqDTO> save(@RequestBody FaqDTO faqDTO) {
        return super.save(faqDTO);
    }

    @GetMapping("get_all")
    public Result<List<FaqDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("get_by_id")
    public Result<FaqDTO> getByID(@RequestParam("id") long id) {
        return super.getByID(id);
    }

    @DeleteMapping("delete")
    public Result deleteById(@RequestParam("id") long id) {
        return super.deleteById(id);
    }
}
