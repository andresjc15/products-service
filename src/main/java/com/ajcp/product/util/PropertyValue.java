package com.ajcp.product.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyValue {

    @Value("${path.products}")
    public String port;

}
