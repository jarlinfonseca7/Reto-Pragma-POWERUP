package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.ObjectRequestDto;
import com.pragma.powerup.application.dto.response.ObjectResponseDto;

import java.util.List;

public interface IObjectHandler {

    void saveObject(ObjectRequestDto objectRequestDto);

    List<ObjectResponseDto> getAllObjects();
}