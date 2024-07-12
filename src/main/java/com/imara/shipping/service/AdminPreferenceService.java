package com.imara.shipping.service;

import com.imara.shipping.model.AdminPreference;
import com.imara.shipping.repository.AdminPreferenceRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class AdminPreferenceService extends AbstractService<AdminPreference> {
    @Autowired
    private AdminPreferenceRepository upRepository;

    @Autowired
    private SystemPreferenceService spService;

    @Override
    protected AbstractRepository getRepository() {
        return upRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    public AdminPreference save(AdminPreference theUP) {
        if (!this.upRepository.findByNameAndSkipId(theUP.getName(), theUP.getId()).isEmpty()) {
            throw new RuntimeException("This name already used");
        }
        return super.save(theUP);
    }

    public AdminPreference findByName(String name) {
        return upRepository.findByName(name);
    }

    public AdminPreference
    updateValueByName(String name, String value) {
        AdminPreference rec = upRepository.findByName(name);
        if (rec == null) {
            throw new RuntimeException("User preference not found");
        }
        //
        rec.setValue(value);
        return super.save(rec);
    }

    public void savePreferences(List<AdminPreference> adminPreferencesList) {
        Iterator<AdminPreference> adminPreferences = adminPreferencesList.iterator();
        while (adminPreferences.hasNext()) {
            AdminPreference newPreference = adminPreferences.next();
            AdminPreference adminPreference = findById(newPreference.getId());
            if (adminPreference == null) {
                save(newPreference);
            }
        }

    }
}