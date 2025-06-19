package br.com.yhugorocha.social.mapper;

import br.com.yhugorocha.social.dto.UserRequestDTO;
import br.com.yhugorocha.social.dto.UserResponseDTO;
import br.com.yhugorocha.social.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserResponseDTO userResponseDTO(User user);
    User userResponseDTOToUser(UserRequestDTO userResponseDTO);
}
