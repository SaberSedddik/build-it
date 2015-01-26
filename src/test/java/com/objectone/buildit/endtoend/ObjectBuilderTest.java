package com.objectone.buildit.endtoend;

import com.objectone.buildit.ObjectBuilder;
import org.junit.Test;

import static com.objectone.buildit.BuildIt.a;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by saberseddik on 15-01-25.
 */
public class ObjectBuilderTest {

    @Test
    public void buildACustomObjectUsingItsFieldsName(){
        Person expected = new Person();
        expected.setName("saber");
        expected.setLastName("seddik");

        Person result= a(Person.class).having("name","saber").having("lastName","seddik").build();

        assertThat(result, equalTo(expected));
    }
}
