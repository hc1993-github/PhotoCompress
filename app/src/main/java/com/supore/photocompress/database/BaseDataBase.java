package com.supore.photocompress.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.supore.photocompress.bean.Person;
import com.supore.photocompress.dao.PersonDao;

@Database(entities = {Person.class},version = 1,exportSchema = false)
public abstract class BaseDataBase extends RoomDatabase {
    public abstract PersonDao personDao();
}
