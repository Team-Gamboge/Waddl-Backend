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
}
