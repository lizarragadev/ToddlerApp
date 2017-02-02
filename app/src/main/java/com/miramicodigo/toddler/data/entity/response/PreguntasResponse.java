package com.miramicodigo.toddler.data.entity.response;

import com.miramicodigo.toddler.data.entity.PreguntaEntity;

/**
 * Created by gusn8 on 02-02-17.
 */

public class PreguntasResponse extends BaseResponse {

    private PreguntaEntity data;

    public PreguntaEntity getData() {
        return data;
    }

    public void setData(PreguntaEntity data) {
        this.data = data;
    }
}
