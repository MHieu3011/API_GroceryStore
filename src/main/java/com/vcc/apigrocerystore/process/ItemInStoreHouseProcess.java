package com.vcc.apigrocerystore.process;

import com.vcc.apigrocerystore.entities.ItemEntity;
import com.vcc.apigrocerystore.entities.StoreHouseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ItemInStoreHouseProcess {

    CompletableFuture<List<ItemEntity>> findAllItem() throws Exception;

    CompletableFuture<List<StoreHouseEntity>> findAllStoreHouse() throws Exception;
}
