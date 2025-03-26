package pet.project.userservice.mapper;

import pet.project.userservice.model.dto.response.UserProfileDtoResponse;
import pet.project.userservice.model.dto.UserShortProfileDto;
import pet.project.userservice.model.entity.User;

import java.util.List;

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

    public static List<UserShortProfileDto> mapUsersToShortProfiles(List<User> users) {

        return users.stream()
                .map(user -> UserShortProfileDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .bio(user.getBio())
                        .avatar(user.getAvatar())
                        .build())
                .toList();
    }
}
