package com.petlife.pet.entity;

import javax.persistence.AttributeConverter;


public class Converter implements AttributeConverter<String, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(String value) {
        if (value != null) {
            if (value.trim().toUpperCase().equals("狗") ) {
                return false;
            } else {
                return true;
            }

        }
        return null;
    }

    @Override
    public String convertToEntityAttribute(Boolean value) {
        if (value != null) {
        	if (value) {
                return "貓";
            } else {
                return "狗";
            }
        }
        return null;
    }

}

