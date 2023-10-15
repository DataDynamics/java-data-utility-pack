package io.datadynamics.utility;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TypeMapping {

    Map<String, String> mapping;

    String defaultType;

    public String getType(String type) {
        String cast = mapping.get(type);
        return StringUtils.isEmpty(cast) ? defaultType : cast;
    }

    public Map<String, String> getMapping() {
        if (mapping == null) {
            this.mapping = new HashMap<>();
        }
        return mapping;
    }

}
