package com.elwynn94.onetomany.service;

import com.elwynn94.onetomany.entity.Child;
import com.elwynn94.onetomany.entity.Parent;
import com.elwynn94.onetomany.repository.ChildRepository;
import com.elwynn94.onetomany.repository.ParentRepository;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParentServiceTest {
    @Autowired
    private ParentService parentService;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ChildRepository childRepository;

    @Test
    public void testSaveParentWithChildren_issueReproduction() {
        // Given
        Parent parent = new Parent();
        parent.setName("Parent 1");

        Child child1 = new Child();
        child1.setName("Child 1");

        Child child2 = new Child();
        child2.setName("Child 2");

        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        // When
        Parent savedParent = parentService.saveParentWithChildren(parent);

        // Then
        assertNotNull(savedParent.getId());
        assertEquals(2, savedParent.getChildren().size());
    }

    @Test
    public void testSaveParentWithChildren_fix() {
        // Given
        Parent parent = new Parent();
        parent.setName("Parent 1");

        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
                .build();

        List<Child> children = fixtureMonkey.giveMe(Child.class, 2);
        children.forEach(child -> child.setName("Child " + child.getId()));
        parent.getChildren().addAll(children);

        // When
        Parent savedParent = parentService.saveParentWithChildren(parent);

        // Then
        assertNotNull(savedParent.getId());
        assertEquals(2, savedParent.getChildren().size());
    }
}