package com.czareg.school.feature.child;

import com.czareg.school.feature.parent.Parent;
import com.czareg.school.feature.school.School;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChildTest {

    @Test
    void testEquals() {
        Parent parent = new Parent();
        School school = new School();
        Child child1 = new Child(1L, "John", "Doe", parent, school);
        Child child2 = new Child(1L, "John", "Doe", parent, school);
        Child child3 = new Child(2L, "John", "Doe", parent, school);

        assertNotSame(child1, child2);
        assertNotSame(child2, child3);
        assertTrue(child1.equals(child2));
        assertTrue(child2.equals(child1));
        assertFalse(child2.equals(child3));
        assertFalse(child3.equals(child2));
        assertFalse(child1.equals(child3));
        assertFalse(child3.equals(child1));
    }

    @Test
    void testHashCode() {
        Parent parent = new Parent();
        School school = new School();
        Child child1 = new Child(1L, "John", "Doe", parent, school);
        Child child2 = new Child(1L, "John", "Doe", parent, school);

        assertEquals(child1.hashCode(), child2.hashCode());
    }

    @Test
    void testChildList() {
        Parent parent = new Parent();
        School school = new School();
        Child child = new Child(1L, "John", "Doe", parent, school);
        List<Child> childList = new ArrayList<>();

        childList.add(child);

        Assertions.assertTrue(childList.contains(child));
        childList.remove(child);
        Assertions.assertFalse(childList.contains(child));
    }
}