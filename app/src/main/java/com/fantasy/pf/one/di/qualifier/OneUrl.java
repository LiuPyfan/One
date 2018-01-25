package com.fantasy.pf.one.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/22.
 * let none that wait on thee be ashamed
 *
 * @Qualifier 限定构造函数对象里的不同参数
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface OneUrl {
}
