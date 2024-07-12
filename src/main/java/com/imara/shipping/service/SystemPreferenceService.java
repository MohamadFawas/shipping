package com.imara.shipping.service;

import com.imara.shipping.model.*;
import com.imara.shipping.repository.SystemPreferenceRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SystemPreferenceService extends AbstractService<SystemPreference> {
    @Autowired
    private SystemPreferenceRepository systemPreferenceRepository;

    @Autowired
    private PreferenceGroupService preferenceGroupService;

    @Override
    protected AbstractRepository getRepository() {
        return systemPreferenceRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    public SystemPreference save(SystemPreference theSP) {
        if (!this.systemPreferenceRepository.findByNameAndSkipId(theSP.getName(), theSP.getId()).isEmpty()) {
            throw new RuntimeException("This name already used");
        }
        // check if the preference group id is valid
        if (theSP.getPreferenceGroupId() > 0) {
            PreferenceGroup pg = this.preferenceGroupService.findById(theSP.getPreferenceGroupId());
            if (pg == null) {
                throw new RuntimeException("Invalid preference group ID");
            }
        } else {
            throw new RuntimeException("Preference group Id is required");
        }
        //
        return super.save(theSP);
    }

    public List<SystemPreference> findAllByPreferenceGroupId(long preferenceGroupId) {
        return systemPreferenceRepository.findAllByPreferenceGroupId(preferenceGroupId);
    }

    public SystemPreference findByName(String name) {
        return systemPreferenceRepository.findByName(name);
    }

    public SystemPreference updateValueByName(String name, String value) {
        SystemPreference rec = systemPreferenceRepository.findByName(name);
        if (rec == null) {
            throw new RuntimeException("System preference not found");
        }
        //
        rec.setValue(value);
        return super.save(rec);
    }
}