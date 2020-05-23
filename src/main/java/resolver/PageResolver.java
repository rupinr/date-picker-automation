package resolver;

import page.BasePageInterface;

import java.lang.reflect.InvocationTargetException;
import config.Config;

public class PageResolver {

    public static <PageInterface extends  BasePageInterface> PageInterface createMatchingPage(Class<? extends PageInterface> pageInterface, Object... params){
        PageInterface returnPage = null;
        try {
            String ClassName = pageInterface.getPackage().getName()+getFullyQualifiedClassName((Config) params[1])+ pageInterface.getSimpleName();
            Class<?> clazz=  Class.forName(ClassName);
            returnPage= (PageInterface) clazz.getConstructors()[0].newInstance(params[0]);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return returnPage;

    }
    private static String getFullyQualifiedClassName(Config config) {
        String classPrefix = "";
        switch (config.getOs()) {
            case IOS: {
                classPrefix = "IOS";
                break;
            }
            case ANDROID: {
                classPrefix = "Android";
                break;
            }
        }
        return "."+config.getOs().toString().toLowerCase()+"."+config.getVersion()+"."+classPrefix;

    }
}
