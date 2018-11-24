package com.chj.springbootdemo.service.dto;
import com.chj.springbootdemo.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends User {

    private String startTime;
    private String endTime;
}
