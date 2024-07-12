package com.imara.shipping.model.core;

import com.imara.shipping.utility.UserUtility;
import lombok.Data;

import javax.persistence.*;

import com.imara.shipping.utility.CurrentTimeUtility;

import java.time.*;

@Data
@MappedSuperclass
public abstract class AbstractObject {

    private LocalDateTime timestampC;
    private LocalDateTime timestampU;
    private int recStatus;      // is logically deleted or not
    private long createdBy;
    private long updatedBy;

    public abstract long getId();

    public abstract void setId(long id);

    public void setDefaultValues() {
        LocalDateTime now = CurrentTimeUtility.getCurrentTime();
        if (getId() == 0) {
            setTimestampC(now);
            createdBy = UserUtility.getCurrentUserId();
        } else {
            setTimestampU(now);
            updatedBy = UserUtility.getCurrentUserId();
        }
    }

}