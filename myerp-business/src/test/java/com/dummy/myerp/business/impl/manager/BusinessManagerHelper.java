package com.dummy.myerp.business.impl.manager;

import com.dummy.myerp.business.impl.AbstractBusinessManager;

public class BusinessManagerHelper extends AbstractBusinessManager {

    public BusinessManagerHelper() {
        super();
    }

    public static BusinessManagerHelper getInstance() {
        return new BusinessManagerHelper();
    }
}
