package io.ikka.bqadapter.controller;

import io.ikka.bqadapter.dto.RequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Slf4j
@RestController
public class TestController {

    @PostMapping("/object")
    public ResponseEntity processObject(@RequestBody RequestDTO requestDTO) {
        Class clazz = null;
        try {
            clazz = Class.forName("com.arqatech.qort.wsapi." + requestDTO.getClassName());
            Object o = BeanUtils.instantiateClass(clazz);
            BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(o);
            for (Map.Entry<String, String> stringStringEntry : requestDTO.getFields().entrySet()) {
                beanWrapper.setPropertyValue(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
            log.info("");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(requestDTO);
    }
}
