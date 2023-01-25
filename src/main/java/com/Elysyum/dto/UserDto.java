package com.Elysyum.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @PositiveOrZero
    private Long id;

    @NotNull
    @Size(min = 4, max = 32)
    private String username;

    private String password;

    private String email;


}
