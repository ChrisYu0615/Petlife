package com.petlife.pet.entity;

import javax.persistence.AttributeConverter;




public class GenderConverter implements AttributeConverter<String, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(String value) {
        if (value != null) {
            if (value.trim().toUpperCase().equals("母") ) {
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
                return "公";
            } else {
                return "母";
            }
        }
        return null;
    }

}

