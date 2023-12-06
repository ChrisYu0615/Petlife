package com.petlife.pet.entity;

import javax.persistence.AttributeConverter;


public class PetLigationConverter implements AttributeConverter<String, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(String value) {
        if (value != null) {
            if (value.trim().toUpperCase().equals("未絕育") ) {
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
                return "有絕育";
            } else {
                return "未絕育";
            }
        }
        return null;
    }

}

