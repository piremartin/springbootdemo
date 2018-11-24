package com.chj.springbootdemo.service.mapper;

import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.service.dto.UserDTO;
import com.chj.springbootdemo.web.rest.vm.UserVM;
import com.chj.springbootdemo.web.rest.vo.UserVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO vmToDto(UserVM vm);

    User toEntity(UserDTO dto);

    UserDTO toDTO(User user);

    UserVO toVO(UserDTO dto);
}
