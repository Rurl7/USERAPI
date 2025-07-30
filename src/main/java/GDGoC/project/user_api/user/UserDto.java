package GDGoC.project.user_api.user;

public record UserDto(
        Integer id,
        String userId,
        String username,
        String phone
) {
    public static UserDto from(User u) {
        return new UserDto(u.getId(), u.getUserId(), u.getUsername(), u.getPhone());
    }
}
