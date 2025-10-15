package com.hbtn.zafirasolidaire.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BaseModelTest {
    static class BaseEntityTest extends BaseModel{

        public void setId(UUID id) {

            super.id = id;
        }
    }

    @Test
    public void BaseModelEntityCreationTest() {

        //Arrange
        BaseEntityTest baseEntity =  new BaseEntityTest();

        //Act
        UUID uuId = UUID.randomUUID();
        baseEntity.setId(uuId);
        baseEntity.onCreate();

        LocalDateTime createdAt = baseEntity.getCreateDate();
        UUID baseEntityId = baseEntity.getId();


        try {
            // simulate 10 ms delay to test diff between update date and create date
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        baseEntity.onUpdate();
        LocalDateTime updatedAt = baseEntity.getUpdateDate();

        //Assert

        Assertions.assertThat(createdAt).isNotNull();
        Assertions.assertThat(updatedAt).isNotNull();
        Assertions.assertThat(baseEntityId).isNotNull();
        //Assertions.assertThat(createdAt).isEqualTo(updatedAt);
        Assertions.assertThat(updatedAt).isAfter(createdAt);



        System.out.println("entity created at " + createdAt);
        System.out.println("entity updated at " + updatedAt);
        System.out.println("entity id: " + baseEntityId);

    }
}
