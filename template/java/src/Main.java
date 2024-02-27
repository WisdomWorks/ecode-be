import java.lang.reflect.Method;
import java.lang.*;
import java.util.*;
public class Main {
    public static void callMethod(
            boolean isCodeRunning, String className, String methodName,
            String expectedOutputType, String expectedOutput,
            String[] parameterTypes, Object... args) {
        try {
            Class<?> targetClass = Class.forName(className); // convert string classname to class
            Object instance = targetClass.newInstance(); // invoke empty constructor

            Class<?>[] paramTypes = new Class<?>[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                paramTypes[i] = mapTypeNameToClass(parameterTypes[i]);
            }

            Method method = instance.getClass().getMethod(methodName, paramTypes);
            Object result = method.invoke(instance, args);
            if (isCodeRunning) {
                System.out.print(result);
            } else {
                if ((result.getClass().getSimpleName().equals(expectedOutputType)) && (String.valueOf(result).equals(expectedOutput))) {
                    System.out.println("true");
                }
                else {
                    System.out.println("false");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class<?> mapTypeNameToClass(String typeName) {
        switch (typeName) {
            case "Integer":
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

    public static Object[] convertParameters(String[] parameters, String[] parameterTypes) {
        List<Object> convertedParameters = new ArrayList<>();
        try {
            for (int i = 0; i < parameters.length; i++) {
                String parameterType = parameterTypes[i];
                String parameterName = parameters[i];

                Class<?> parameterClass = mapTypeNameToClass(parameterType);

                if (parameterClass == int.class) {
                    int intValue = Integer.parseInt(parameterName);
                    convertedParameters.add(intValue);
                } else if (parameterClass == double.class) {
                    double doubleValue = Double.parseDouble(parameterName);
                    convertedParameters.add(doubleValue);
                } else {
                    Object instance = parameterClass.newInstance();
                    convertedParameters.add(instance);
                }
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return convertedParameters.toArray();
    }

    public static void main(String[] args) throws Exception {
        String className = "Calculator";
        String methodName = "add";
        boolean isCodeRunning = true;
        String [][] inputs = {{"1", "2"}, {"2", "3"}, {"5", "3"}};
        String [][] inputTypes = {{"Integer", "Integer"}, {"Integer", "Integer"}, {"Integer", "Integer"}};
        String[] outputs = {"3", "2", "8"};
        String[] outputTypes = {"Integer", "Integer", "String"};

        for (int i = 0; i < inputs.length; i++){
            String[] parameterTypes = inputTypes[i];
            String[] parameters = inputs[i];
            String expectedOutput = outputs[i];
            String expectedOutputType = outputTypes[i];

            Object[] convertedParameters = convertParameters(parameters, parameterTypes);

            callMethod(
                    isCodeRunning, className, methodName,
                    expectedOutputType, expectedOutput,
                    parameterTypes, Arrays.stream(convertedParameters).toArray());
        }
    }
}