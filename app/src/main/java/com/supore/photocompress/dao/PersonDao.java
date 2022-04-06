package com.supore.photocompress.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.supore.photocompress.bean.Person;

@Dao
public interface PersonDao {
    @Insert
    void insertPerson(Person person);
    @Delete
    void deletePerson(Person person);
    @Query("SELECT * FROM Person WHERE name=:name")
    Person queryByName(String name);
}
