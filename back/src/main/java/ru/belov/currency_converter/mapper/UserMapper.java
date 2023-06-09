package ru.belov.currency_converter.mapper;

import org.springframework.stereotype.Component;
import ru.belov.currency_converter.dto.user.UserDtoIn;
import ru.belov.currency_converter.dto.user.UserDtoOut;
import ru.belov.currency_converter.entity.Users;

@Component
public class UserMapper {

    public Users toEntity(UserDtoIn dtoIn) {
        if (dtoIn == null) {
            return null;
        }

        Users user = new Users();
        user.setId(dtoIn.getId());
        user.setPassword(dtoIn.getPassword());

        return user;
    }

    public UserDtoOut toDto(Users user) {
        if (user == null) {
            return null;
        }

        UserDtoOut dtoOut = new UserDtoOut();
        dtoOut.setId(user.getId());

        return dtoOut;
    }
}
