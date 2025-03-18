package pet.project.userservice.constant;

public class ErrorMessagesUtil {

    public static final String USER_ALREADY_EXISTS = "Such username already exists!";

    public static final String USER_NOT_FOUND_BY_ID = "User with such id does not exist!";

    public static final String USER_NOT_FOUND_BY_USERNAME = "User with such username does not exist!";

    public static final String USER_NOT_FOUND_DETAILS = "Username has been entered is not correct.";

    public static final String WRONG_TOKEN_TYPE = "Type of the token is not Bearer!";

    public static final String AUTH_HEADER_IS_MISSING = "Authorization header is missing!";

    public static final String FORBIDDEN_TO_UPDATE = "Not allowed to update user profile!";

    public static final String ALL_PARAMS_ARE_NULL = "At least one parameter must be not null!";

    public static final String FORBIDDEN_REQUEST = "Forbidden request!";

    public static final String FRIEND_ID_MATCH_USER_ID = "Friend id can't match user id!";

    public static final String FRIENDSHIP_NOT_FOUND = "Friendship does not exist!";
}
