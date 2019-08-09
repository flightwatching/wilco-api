package com.fw.wilco.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by dao on 06/06/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionComponent {

    Class value();
}
