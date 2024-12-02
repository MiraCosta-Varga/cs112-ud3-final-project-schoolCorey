package cs112.ud3.models;

public abstract class ValidLink {
    /**
     * Creates an array of all possible valid versions of objects for the class to link to
     */
    abstract void createValidArray();

    /**
     * Checks if the provided object is valid by checking if it is contained within the array of valid objects
     * @param candidate The object to check the validity of
     * @return True if the object is valid, false if it is not.
     */
    abstract boolean objectIsValid(Object candidate);
}
