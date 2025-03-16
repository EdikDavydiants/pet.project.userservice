package pet.project.userservice.mapper;

import pet.project.userservice.model.dto.response.UserProfileDtoResponse;
import pet.project.userservice.model.entity.User;

public class UserMappers {

    public static UserProfileDtoResponse mapUserToFullUserProfileDto(User user) {

         return UserProfileDtoResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .bio(user.getBio())
                .build();
    }

    public static UserProfileDtoResponse mapUserToUserProfileDto(User user) {

        return UserProfileDtoResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .avatar(user.getAvatar())
                .bio(user.getBio())
                .build();
    }
}
