package pet.project.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pet.project.userservice.exception.ForbiddenAccessException;
import pet.project.userservice.model.dto.SimpleDtoResponse;
import pet.project.userservice.service.FriendshipService;

import static pet.project.userservice.constant.ErrorMessagesUtil.FORBIDDEN_REQUEST;
import static pet.project.userservice.utils.JwtUtils.extractIdFromJwt;
import static pet.project.userservice.utils.JwtUtils.extractTokenFromHeader;

@RestController
@RequiredArgsConstructor
public class FriendshipController {

    private final FriendshipService friendshipService;

    @PostMapping("/{id}/friends/{friendId}")
    public SimpleDtoResponse sendFriendshipRequest(
            @RequestHeader(value = "Authorization") String authHeader,
            @PathVariable long id, @PathVariable long friendId) {

        checkPermission(authHeader, id);
        return friendshipService.sendFriendshipRequest(id, friendId);
    }

    @PostMapping("/{id}/friends/{friendId}/accept")
    public SimpleDtoResponse acceptFriendship(
            @RequestHeader(value = "Authorization") String authHeader,
            @PathVariable long id, @PathVariable long friendId) {

        checkPermission(authHeader, id);
        return friendshipService.acceptFriendshipRequest(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public SimpleDtoResponse deleteFriendship(
            @RequestHeader(value = "Authorization") String authHeader,
            @PathVariable long id, @PathVariable long friendId) {

        checkPermission(authHeader, id);
        return friendshipService.deleteFriendship(id, friendId);
    }

    public void checkPermission(String authHeader, long id) {

        String jwt = extractTokenFromHeader(authHeader);
        long headerId = extractIdFromJwt(jwt);
        if(headerId != id) {
            throw new ForbiddenAccessException(FORBIDDEN_REQUEST);
        }
    }
}
