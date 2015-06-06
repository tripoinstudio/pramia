package com.tripoin.tripoin_model.nativemodel;

import android.content.ContentValues;
import java.util.List;

/**
 * Created by Achmad Fauzi on 12/22/2014.
 * achmad.fauzi@sigma.co.id
 */

public interface IBaseDataSource<T> {

    public void insert(String tableName, ContentValues contentValues);

    public void update(T model);

    public void delete(String id, String tableName);

    public T getById(Integer id);

    public List<T> getAll();

}
