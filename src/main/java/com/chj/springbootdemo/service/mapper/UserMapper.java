package com.chj.springbootdemo.service.mapper;

import com.chj.springbootdemo.domain.User;
import com.chj.springbootdemo.service.dto.UserDTO;
import com.chj.springbootdemo.web.rest.vm.UserVM;
import com.chj.springbootdemo.web.rest.vo.UserVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO vmToDto(UserVM vm);

    User toEntity(UserDTO dto);

    List<UserDTO> toDTO(List<User> entityList);
    UserDTO toDTO(User user);

    List<UserVO> toVO(List<UserDTO> dtoList);

    UserVO toVO(UserDTO dto);
}
