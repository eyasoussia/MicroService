package com.usermanagement.Mapper;

import com.usermanagement.dto.UserDto;

import com.usermanagement.model.UserEntity;

import java.util.List;


public class UserMapper {

    public static UserEntity toEntity(UserDto userDto) {
        return new UserEntity(
                null,
                userDto.name(),
                userDto.email(),
                userDto.username(),
                userDto.password(),
                userDto.role()
        );
    }

    public static UserDto toDto(UserEntity userEntity) {
        return new UserDto(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRole(),
                List.of()

        );
    }
}
