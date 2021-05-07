package me.kjs.di;

import java.util.Arrays;

public class ContainerService {

  public static <T> T getObject(Class<T> classType) {
    T instance = createInstance(classType);
    Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
      Inject inject = f.getAnnotation(Inject.class);
      if (inject != null) {
        Object fieldInstance = createInstance(f.getType());
        f.setAccessible(true);
        try {
          f.set(instance, fieldInstance);
        } catch (IllegalAccessException e) {
          throw new RuntimeException();
        }
      }
    });
    return instance;
  }

  private static <T> T createInstance(Class<T> classType) {
    try {
      return classType.getConstructor(null).newInstance();
    } catch (InstantiationException | IllegalAccessException | java.lang.reflect.InvocationTargetException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }

  }
}
