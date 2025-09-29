package com.hbtn.zafirasolidaire.model;

import java.util.Date;
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
        final BaseEntityTest baseEntity =  new BaseEntityTest();

        //Act
        UUID uuId = UUID.randomUUID();
        baseEntity.setId(uuId);
        baseEntity.onCreate();

        Date createdAt = baseEntity.getCreateDate();
        UUID baseEntityId = baseEntity.getId();


        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // simulate delay
        baseEntity.onUpdate();
        Date updatedAt = baseEntity.getUpdateDate();

        //Assert
        // assertNotNull(updatedAt, "Update date was initialized on baseEntity creation");
        // assertNotNull(createdAt,"Create date was initialized on baseEntity creation");
        // assertNotNull(baseEntityId, "Update date was set after baseEntity creation");
        // assertEquals(updatedAt, createdAt, "updatedAt and createdAt should be equal on creation");

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
