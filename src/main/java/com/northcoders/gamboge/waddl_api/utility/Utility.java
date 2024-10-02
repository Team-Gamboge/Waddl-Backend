package com.northcoders.gamboge.waddl_api.utility;

import java.lang.reflect.Field;
import java.util.Collection;

public class Utility {
    
    public static <T> void updateFieldsWhereNotNull(T source, T destination) throws NoSuchFieldException, IllegalAccessException {
        Field[] sourceFields = source.getClass().getDeclaredFields();

        for (Field sourceField: sourceFields) {
            Boolean sourceAccessibility = sourceField.isAccessible();
            Boolean destinationAccessibility = false;
            Object sourceFieldObj;
            Field destinationField = null;
            sourceField.setAccessible(true);
            sourceFieldObj = sourceField.get(source);
            sourceField.setAccessible(sourceAccessibility);

            if (sourceFieldObj instanceof Collection<?>) {
                if (((Collection<?>) sourceFieldObj).isEmpty())
                    continue;
            }

            if (sourceFieldObj == null)
                continue;

            try {
                destinationField = destination.getClass().getDeclaredField(sourceField.getName());
                destinationAccessibility = destinationField.isAccessible();
                destinationField.setAccessible(true);
                destinationField.set(destination, sourceFieldObj);

            } catch (NoSuchFieldException | IllegalAccessException e) {
                
                throw e;
            } finally {
                destinationField.setAccessible(destinationAccessibility);
            }

        }
    }

    public static <T> Boolean containsNullNonPrimitiveFields(T instance) throws IllegalAccessException {
        try {
            Field[] fields = instance.getClass().getDeclaredFields();

            for (Field field: fields) {
                boolean wasOriginallyAccessible = field.isAccessible();

                if (!wasOriginallyAccessible)
                    field.setAccessible(true);

                if (field.get(instance) == null) {
                    if (!wasOriginallyAccessible)
                        field.setAccessible(true);

                    return true;
                }

                if (!wasOriginallyAccessible)
                    field.setAccessible(false);
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException |
                 ExceptionInInitializerError | NullPointerException | ClassCastException | UnsupportedOperationException e) {
            throw e;
        }

        return false;
    }

    public static <T> Boolean containsBlankStringFields(T instance) throws IllegalAccessException {
        try {
            Field[] fields = instance.getClass().getDeclaredFields();

            for (Field field: fields) {
                boolean wasOriginallyAccessible = field.isAccessible();

                if (!wasOriginallyAccessible)
                    field.setAccessible(true);

                Object instanceField = field.get(instance);

                if (!(instanceField instanceof String))
                    continue;

                if (((String)instanceField).isBlank()) {
                    if (!wasOriginallyAccessible)
                        field.setAccessible(true);

                    return true;
                }

                if (!wasOriginallyAccessible)
                    field.setAccessible(false);
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException |
                 ExceptionInInitializerError | NullPointerException | ClassCastException | UnsupportedOperationException e) {
            throw e;
        }

        return false;
    }
}
