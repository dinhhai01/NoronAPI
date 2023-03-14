package com.mh.data.mapper;

import com.mh.data.reponse.TopicResponse;
import com.mh.dto.PostTopicDTO;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;
import org.mapstruct.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class TopicMapper {

    public abstract TopicResponse toResponse (PostTopicDTO topic);

//    @Named("toRs")
//    public abstract TopicResponse topicResponse(PostTopicDTO postTopicDTO, @Context Integer id);
//
//    @IterableMapping(qualifiedByName = "toRs")
//    public abstract List<TopicResponse> topicResponses(List<PostTopicDTO> postTopicDTOS,  @Context Integer id);
//
//    @AfterMapping
//    public void afterMapping(@MappingTarget TopicResponse topicResponse,PostTopicDTO postTopicDTO,
//                             @Context Integer id){
//        if(Objects.equals(postTopicDTO.getPostId(),id)){
//            topicResponse = toResponse(postTopicDTO);
//        }
//    }
}
