package com.anyway.admin.model.convert;

import com.anyway.admin.model.domain.UserDO;
import com.anyway.admin.model.dto.UserDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: wang_hui
 * @date: 2018/10/10 下午3:21
 */
@Mapper
public interface UserConvert {
    UserConvert MAPPER = Mappers.getMapper(UserConvert.class);

    UserDTO do2dto(UserDO userDO);

    List<UserDTO> dos2dtos(List<UserDO> list);

    @InheritInverseConfiguration
    UserDO dto2do(UserDTO userDTO);
}
