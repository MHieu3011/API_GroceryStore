package com.vcc.apigrocerystore.process;

import com.vcc.apigrocerystore.dao.ItemDAO;
import com.vcc.apigrocerystore.dao.StoreHouseDAO;
import com.vcc.apigrocerystore.entities.ItemEntity;
import com.vcc.apigrocerystore.entities.StoreHouseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class ItemInStoreHouseProcessImpl extends AbstractProcess implements ItemInStoreHouseProcess {

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private StoreHouseDAO storeHouseDAO;

    @Override
    public CompletableFuture<List<ItemEntity>> findAllItem() throws Exception {
        return CompletableFuture.completedFuture(findAllItemFunc());
    }

    @Override
    public CompletableFuture<List<StoreHouseEntity>> findAllStoreHouse() throws Exception {
        return CompletableFuture.completedFuture(findAllStoreHouseFunc());
    }


    private List<ItemEntity> findAllItemFunc() {
        return new ArrayList<>();
    }

    private List<StoreHouseEntity> findAllStoreHouseFunc() {
        return new ArrayList<>();
    }
}
