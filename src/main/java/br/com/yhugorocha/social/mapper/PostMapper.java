package br.com.yhugorocha.social.mapper;

import br.com.yhugorocha.social.dto.PostRequestDTO;
import br.com.yhugorocha.social.dto.PostResponseDTO;
import br.com.yhugorocha.social.entities.Post;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostMapper {

    PostResponseDTO postResponseDTO(Post post);
    Post postResponseDTOToPost(PostRequestDTO requestDTO);
}
