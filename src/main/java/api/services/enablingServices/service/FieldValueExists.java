package api.services.enablingServices.service;

public interface FieldValueExists {
    /**
     * Checks whether or not a given value exists for a given field
     *
     * @param value      The value to check for
     * @param columnName The name of the field for which to check if the value exists
     * @return True if the value exists for the field; false otherwise
     * @throws UnsupportedOperationException
     */
    boolean fieldValueExists(Object value, String columnName) throws UnsupportedOperationException;
}