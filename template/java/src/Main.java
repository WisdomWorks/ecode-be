import java.lang.reflect.Method;
public class Main {
    public static void callMethod(String className, String methodName, String[] parameterTypes, Object... args) {
        try {
            Class<?> targetClass = Class.forName(className); // convert string classname to class
            Object instance = targetClass.newInstance(); // invoke empty constructor

            Class<?>[] paramTypes = new Class<?>[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                paramTypes[i] = mapTypeNameToClass(parameterTypes[i]);
            }

            Method method = instance.getClass().getMethod(methodName, paramTypes);
            System.out.print(method.invoke(instance, args));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class<?> mapTypeNameToClass(String typeName) {
        switch (typeName) {
            case "int":
                return int.class;
            case "double":
                return double.class;
            default:
                try {
                    return Class.forName(typeName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
        }
    }
    public static void main(String[] args) throws Exception {
        String className = "Calculator";
        String methodName = "add";
        String[] parameterTypes = {"int", "int"};
        boolean isCodeRunning = true;

        if(isCodeRunning) {
            callMethod(className, methodName, parameterTypes, 1, 2);
        }
    }
}