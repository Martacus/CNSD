package com.mart.command.person;

import com.mart.events.command.CreatePerson;
import com.mart.events.event.PersonCreated;
import com.mart.events.model.Person;
import org.axonframework.common.Assert;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonTest {

    FixtureConfiguration<Person> fixture;

    @Before
    public void setup(){
        fixture = new AggregateTestFixture<>(Person.class);
    }

    @Test
    public void testCreatePerson(){
        UUID simpleid = UUID.randomUUID();
        fixture.givenNoPriorActivity()
                .when(new CreatePerson(simpleid, LocalDate.of(2018, 2, 2)))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new PersonCreated(simpleid, LocalDate.of(2018, 2, 2)))
                .expectState(state ->{
                     assertThat(state.id, is(simpleid));
        });


    }

}
