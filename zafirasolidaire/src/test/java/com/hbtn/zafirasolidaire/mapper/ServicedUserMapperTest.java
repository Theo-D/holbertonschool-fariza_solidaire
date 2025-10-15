package com.hbtn.zafirasolidaire.mapper;

import org.junit.jupiter.api.Test;

import com.hbtn.zafirasolidaire.dto.RequestServicedUserDto;
import com.hbtn.zafirasolidaire.dto.ServicedUserDto;
import com.hbtn.zafirasolidaire.model.ServicedUser;
import com.hbtn.zafirasolidaire.model.User;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ServicedUserMapperTest {

    private final ServicedUserMapper mapper = new ServicedUserMapper();

    @Test
    public void testServicedUserToDto() {
        // Arrange
        UUID servicedUserId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        User user = new User()
                .setId(userId);

        ServicedUser servicedUser = new ServicedUser()
                .setId(servicedUserId)
                .setUser(user);

        // Act
        ServicedUserDto dto = mapper.servicedUserToDto(servicedUser);

        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(servicedUserId);
        assertThat(dto.getUserId()).isEqualTo(userId);

        System.out.println("Serviced User: " + servicedUser.toString());
        System.out.println("Mapped DTO: " + dto.toString());
    }

    @Test
public void testDtoToServicedUser() {
    // Arrange
    User user = new User().setId(UUID.randomUUID());

    RequestServicedUserDto dto = new RequestServicedUserDto();
    dto.setUserId(user.getId());

    // Act
    ServicedUser servicedUser = mapper.dtoToServicedUser(dto);

    // Assert
    assertThat(servicedUser).isNotNull();
    assertThat(servicedUser.getUser()).isNotNull();
    assertThat(servicedUser.getId()).isEqualTo(dto.getId()); // check Id
    assertThat(servicedUser.getUser().getId()).isEqualTo(dto.getUserId()); // check userId

    System.out.println("ServicedUserDTO: " + dto);
    System.out.println("Mapped ServicedUser: " + servicedUser);
}

}
