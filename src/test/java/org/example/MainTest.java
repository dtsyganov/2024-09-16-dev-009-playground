package org.example;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MainTest {
    private final LocalDateTime time = LocalDateTime.parse("2021-09-16T10:00:00");
    private final EasyRandom easyRandom = new EasyRandom(
        new EasyRandomParameters()
            .stringLengthRange(4, 32)
            .overrideDefaultInitialization(true)
            .collectionSizeRange(2, 3)
            .objectPoolSize(100)
    );

    @Test
    void test() {
        try (MockedStatic<LocalDateTime> mockedStatic = Mockito.mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(time);

            System.out.println(LocalDateTime.now());

            mockedStatic.verify(LocalDateTime::now);
        }
        System.out.println(LocalDateTime.now());
    }

    @Test
    void test2() {
        MockedStatic<LocalDateTime> mockedStatic = Mockito.mockStatic(LocalDateTime.class);
        mockedStatic.when(LocalDateTime::now).thenReturn(time);
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now());
        mockedStatic.verify(LocalDateTime::now, Mockito.times(2));
        mockedStatic.close();
        System.out.println(LocalDateTime.now());
    }


    @Test
    void test3() {
        Person person = new Person("name", 10);
        Person person1 = easyRandom.nextObject(Person.class);

        System.out.println(person);
        System.out.println(person1);

        assertNotEquals(person.name(), person1.name());
        assertNotEquals(person.age(), person1.age());
    }
}
