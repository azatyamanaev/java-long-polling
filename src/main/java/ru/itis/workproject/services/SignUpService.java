package ru.itis.workproject.services;

import ru.itis.workproject.dto.SignUpDto;

public interface SignUpService {
    Boolean signUp(SignUpDto form);
}
