package br.com.agapesistemas.demo.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class CopyPropsUtil {

    private CopyPropsUtil() {

    }

    public static void copyNonNullProperties(Object source, Object destination) {
        BeanUtils.copyProperties(source, destination, getNullPropertyNames(source));
    }

    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptor = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd : propertyDescriptor) {
            if (src.getPropertyValue(pd.getName()) == null) {
                emptyNames.add(pd.getName());
            }
        }

        return emptyNames.toArray(new String[emptyNames.size()]);
    }
}
