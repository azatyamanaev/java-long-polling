package ru.itis.workproject.services;

import ru.itis.workproject.dto.LogInDto;

public interface LogInService {
    Boolean logIn(LogInDto form);
}
