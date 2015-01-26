package com.objectone.buildit;

/**
 * Created by saberseddik on 15-01-25.
 */
public class BuildIt {

    public static <T> ObjectBuilder<T> a(Class<T> typeToBeBuilt) {
        return new ObjectBuilder<T>(typeToBeBuilt);
    }
}
