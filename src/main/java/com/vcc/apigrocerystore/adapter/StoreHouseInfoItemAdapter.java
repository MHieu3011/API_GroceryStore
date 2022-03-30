package com.vcc.apigrocerystore.adapter;

import com.vcc.apigrocerystore.entities.StoreHouseEntity;
import com.vcc.apigrocerystore.model.response.StoreHouseInfoItemResponse;
import org.springframework.stereotype.Component;

@Component("StoreHouseInfoItemAdapter")
public class StoreHouseInfoItemAdapter implements EntityAdapter<StoreHouseEntity, StoreHouseInfoItemResponse> {
    @Override
    public StoreHouseInfoItemResponse transform(StoreHouseEntity entity) {
        StoreHouseInfoItemResponse result = new StoreHouseInfoItemResponse();
        result.setCodeItem(entity.getCodeItem());
        result.setNumbers(entity.getNumber() + "");
        return result;
    }
}
