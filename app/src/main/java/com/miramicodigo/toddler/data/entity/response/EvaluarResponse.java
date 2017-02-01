package com.miramicodigo.toddler.data.entity.response;

import com.miramicodigo.toddler.data.entity.EvaluarEntity;

/**
 * Created by gusn8 on 30-01-17.
 */

public class EvaluarResponse extends BaseResponse{

    private EvaluarEntity data;

    public EvaluarEntity getData() {
        return data;
    }

    public void setData(EvaluarEntity data) {
        this.data = data;
    }
}
