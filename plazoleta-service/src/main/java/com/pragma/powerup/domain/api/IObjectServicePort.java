package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.ObjectModel;

import java.util.List;

public interface IObjectServicePort {

    void saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}