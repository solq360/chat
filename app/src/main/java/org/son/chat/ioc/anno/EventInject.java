package org.son.chat.ioc.anno;

import org.son.chat.ioc.model.EventType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) 
public @interface EventInject {
	public int[] value();
	public EventType eventType();
}