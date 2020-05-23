package resolver;

import jdk.nashorn.internal.objects.annotations.Optimistic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Platform {

    OS os() ;

    String version() ;
}
