package com.imara.shipping.service;

import com.imara.shipping.controller.core.Result;
import com.imara.shipping.model.PreferenceGroup;
import com.imara.shipping.repository.PreferenceGroupRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PreferenceGroupService extends AbstractService<PreferenceGroup> {
    @Autowired
    private PreferenceGroupRepository preferenceGroupRepository;

    @Autowired
    private SystemPreferenceService systemPreferenceService;

    @Override
    protected AbstractRepository getRepository() {
        return preferenceGroupRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    public PreferenceGroup save(PreferenceGroup theGroup) {
        if (!this.preferenceGroupRepository.findByNameAndSkipId(theGroup.getName(), theGroup.getId()).isEmpty()) {
            throw new RuntimeException("This name already used");
        }
        // check if the parent id is valid
        if (theGroup.getParentId() > 0) {
            PreferenceGroup parentGroup = this.preferenceGroupRepository.findById(theGroup.getParentId()).orElse(null);
            if (parentGroup == null) {
                throw new RuntimeException("Invalid parent preference group ID");
            }
        }
        //
        return super.save(theGroup);
    }

    public List<PreferenceGroup> findAllByParentId(long parentId) {
        return preferenceGroupRepository.findAllByParentId(parentId);
    }

    public Result deleteByIdAndItsChild(long id, int deleteAllChild) {
        PreferenceGroup rec = preferenceGroupRepository.findById(id).orElse(null);
        if (rec == null) {
            throw new RuntimeException("Preference group not found");
        }
        // check if any 'system preferences' linked to this 'preference group'
        if (!systemPreferenceService.findAllByPreferenceGroupId(id).isEmpty()) {
            throw new RuntimeException("System preferences are linked with this preference group");
        }
        // check if this record has child records
        if (preferenceGroupRepository.findAllByParentId(id).isEmpty()) {
            preferenceGroupRepository.deleteById(id);
        } else {
            if (deleteAllChild == 1) {
                preferenceGroupRepository.deleteById(id);
                preferenceGroupRepository.deleteByParentId(id);
            } else {
                throw new RuntimeException("This preference group has child records. Delete all the child records and try again.");
            }
        }

        return new Result();
    }


}
