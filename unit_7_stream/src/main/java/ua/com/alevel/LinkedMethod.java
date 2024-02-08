package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LinkedMethod {

    public void test() {
        IMath iMath = (a, b) -> a + b;
        int sum = iMath.sum(10, 7);
        System.out.println("sum = " + sum);

        iMath = this::sum;
        sum = iMath.sum(11, 7);
        System.out.println("sum = " + sum);

        MathImpl mathImpl = new MathImpl();
        iMath = mathImpl::sum;
        sum = iMath.sum(12, 7);
        System.out.println("sum = " + sum);

        iMath = MathUtil::sum;
        sum = iMath.sum(13, 7);
        System.out.println("sum = " + sum);

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setName(String.valueOf(i));
            user.setPassword(UUID.randomUUID().toString());
            users.add(user);
        }
        System.out.println("users = " + users);

        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDtos.add(userDto);
        }
        System.out.println("userDtos = " + userDtos);

        List<UserDto> userDtoClassicStreamList = users
                .stream()
                .map(u -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(u.getId());
                    userDto.setName(u.getName());
                    return userDto;
                })
                .toList();


        userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(new UserDto(user));
        }

        userDtoClassicStreamList = users
                .stream()
                .map(u -> new UserDto(u))
                .toList();

        userDtoClassicStreamList = users
                .stream()
                .map(UserDto::new)
                .toList();
    }

    private int sum(int a, int b) {
        return a + b;
    }
}
