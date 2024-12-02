package cs112.ud3.models;

import cs112.ud3.Exceptions.UninitializedLinkException;

public abstract class ValidLink {
    /**
     * Creates an array of all possible valid versions of objects for the class to link to
     */
    abstract void createValidArray();

    /**
     * Checks if the provided object is valid by checking if it is contained within the array of valid objects
     * @param candidate The object to check the validity of
     * @return True if the object is valid, false if it is not.
     * @throws UninitializedLinkException Thrown if the method is called before a valid array has been created with createValidArray(),
     *  or if the array has been deleted.
     */
    abstract boolean objectIsValid(Object candidate) throws UninitializedLinkException;
}
