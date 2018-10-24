package com.chj.springbootdemo.service.mapper;

import com.chj.springbootdemo.domain.Person;
import com.chj.springbootdemo.service.dto.PersonDTO;
import com.chj.springbootdemo.web.rest.vo.PersonVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDTO toDTO(Person person);

    PersonVO toVO(PersonDTO personDTO);
}
