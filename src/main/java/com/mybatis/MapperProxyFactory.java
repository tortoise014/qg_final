package com.mybatis;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;


public class MapperProxyFactory {

    private static Map<Class, TypeHandler> typeHandlerMap = new HashMap<>();


    static {
        typeHandlerMap.put(String.class, new StringTypeHandler());
        typeHandlerMap.put(Integer.class, new IntegerTypeHandler());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static <T> T getMapper(Class<T> mapper) {



        Object proxyInstance = Proxy.newProxyInstance(mapper.getClassLoader(), new Class[]{mapper}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


                Connection connection = getConnection();
                try {
                    if (method.isAnnotationPresent(Select.class)) {
                        // 处理 SELECT 操作
                        return handleSelect(connection, method, args);
                    } else if (method.isAnnotationPresent(Insert.class)) {
                        // 处理 INSERT 操作
                        return handleInsert(connection, method, args);
                    } else if (method.isAnnotationPresent(Update.class)) {
                        // 处理 UPDATE 操作
                        return handleUpdate(connection, method, args);
                    } else if (method.isAnnotationPresent(Delete.class)) {
                        // 处理 DELETE 操作
                        return handleDelete(connection, method, args);
                    } else {
                        // 未知操作
                        throw new UnsupportedOperationException("不支持的操作：" + method.getName());
                    }
                } finally {
                    connection.close();
                }

            }
            private Object handleSelect(Connection connection, Method method, Object[] args) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
                // SELECT 操作的实现代码放在这里
                // ...
                Select annotation = method.getAnnotation(Select.class);
                String sql =annotation.value();//获取注解

                //拿到入参数
                Map<String,Object> paramValueMapping =new HashMap<>();
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    Parameter  parameter=parameters[i];
                    String name = parameter.getAnnotation(Param.class).value();

                    paramValueMapping.put(name,args[i]);
                }

                ParameterMappingTokenHandler tokenHandler=new ParameterMappingTokenHandler();
                GenericTokenParser parser=new GenericTokenParser("#{","}",tokenHandler);
                String parseSql = parser.parse(sql);

                List<ParameterMapping> parameterMappings=tokenHandler.getParameterMappings();


                PreparedStatement statement=connection.prepareStatement(parseSql);
                //拿到入参的值
                for (int i=0;i<parameterMappings.size();i++) {
                    String property = parameterMappings.get(i).getProperty();

                    Object value = paramValueMapping.get(property);//根据这个值拿到下面的类型
                    Class<?> type = value.getClass();
                    typeHandlerMap.get(type).setParameter(statement,i+1,value);

                }

                statement.execute();


                ResultSet rs = statement.getResultSet();
                Object result=null;
                List<Object> list=new ArrayList<>();
                Class resultType = null;
                Type genericReturnType = method.getGenericReturnType();
                if (genericReturnType instanceof Class) {
                    // 不是泛型
                    resultType = (Class) genericReturnType;
                } else if (genericReturnType instanceof ParameterizedType) {
                    // 是泛型
                    Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                    resultType = (Class) actualTypeArguments[0];
                }

                ResultSetMetaData metaData = rs.getMetaData();//获取结果集
                List<String> columnList = new ArrayList<>();
                if(metaData.getColumnCount()>0) {
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        columnList.add(metaData.getColumnName(i));
                    }
                }else{
                    System.out.println("没有东西");
                }
                Map<String,Method> setterMethodMapping=new HashMap<>();
                for (Method declaredMethod : resultType.getDeclaredMethods()) {
                    if(declaredMethod.getName().startsWith("set")){
                        String propertyName =declaredMethod.getName().substring(3);
                        propertyName = propertyName.substring(0, 1).toLowerCase(Locale.ROOT) + propertyName.substring(1);
                        setterMethodMapping.put(propertyName,declaredMethod);


                    }
                }



                while(rs.next()){

                    Object instance = resultType.newInstance();//反射获取user对象


                    for (int i = 0; i < columnList.size(); i++) {
                        String column = columnList.get(i);
                        Method setterMethod = setterMethodMapping.get(column);
                        if (setterMethod == null) {
                            // 如果未找到对应的setter方法，可以选择跳过该列的处理或者记录警告信息
                            System.out.println("Warning: Setter method not found for column " + column);
                            continue;
                        }
                        Class clazz = setterMethod.getParameterTypes()[0];
                        TypeHandler typeHandler=typeHandlerMap.get(clazz);
                        setterMethod.invoke(instance,typeHandler.getResult(rs,column));

                    }
                    list.add(instance);

                }



                if (!list.isEmpty()) {
                    if (method.getReturnType().equals(List.class)) {
                        result = list;
                    } else {
                        result = list.get(0);
                    }
                } else {
                    return null;
                }
                return result; // 返回 SELECT 操作的结果
            }

            private Object handleInsert(Connection connection, Method method, Object[] args) throws SQLException {
                Insert annotation = method.getAnnotation(Insert.class);
                String sql = annotation.value();

                try {
                    connection.setAutoCommit(false);

                    Map<String, Object> paramValueMapping = new HashMap<>();
                    Parameter[] parameters = method.getParameters();
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        Param paramAnnotation = parameter.getAnnotation(Param.class);
                        if (paramAnnotation != null) {
                            String name = paramAnnotation.value();
                            Object argValue = args[i];
                            if (argValue != null) {

                                paramValueMapping.put(name, argValue);
                            } else {
                                // 如果参数值为空，则给出默认值或者抛出异常，这里给出一个默认值

                                paramValueMapping.put(name, getDefaultParameterValue(parameter.getType()));
                            }
                        }
                    }

                    ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
                    GenericTokenParser parser = new GenericTokenParser("#{", "}", tokenHandler);
                    String parseSql = parser.parse(sql);
                    List<ParameterMapping> parameterMappings = tokenHandler.getParameterMappings();

                    PreparedStatement statement = connection.prepareStatement(parseSql);
                    for (int i = 0; i < parameterMappings.size(); i++) {
                        String property = parameterMappings.get(i).getProperty();
                        Object value = paramValueMapping.get(property);
                        if (value != null) {
                            Class<?> type = value.getClass();
                            typeHandlerMap.get(type).setParameter(statement, i + 1, value);
                        }
                    }

                    int rowsAffected = statement.executeUpdate();
                    connection.commit();
                    return rowsAffected > 0;
                } catch (SQLException e) {
                    if (connection != null) {
                        connection.rollback();
                    }
                    throw e;
                } finally {
                    if (connection != null) {
                        connection.setAutoCommit(true);
                    }
                }
            }

            private Object getDefaultParameterValue(Class<?> parameterType) {
                if (parameterType == String.class) {
                    return ""; // 字符串类型的默认值为空字符串
                } else if (parameterType == Integer.class || parameterType == int.class) {
                    return 0; // 整数类型的默认值为 0
                } else if (parameterType == Boolean.class || parameterType == boolean.class) {
                    return false; // 布尔类型的默认值为 false
                } else {
                    return null; // 其他类型的默认值为 null
                }
            }
            private Object handleUpdate(Connection connection, Method method, Object[] args) throws SQLException {
                // 获取 Update 注解和 SQL 语句
                Update annotation = method.getAnnotation(Update.class);
                String sql = annotation.value();


                //开启事务,开手动提交
                try {
                    connection.setAutoCommit(false);
                    // 获取方法参数及其注解
                    Map<String, Object> paramValueMapping = new HashMap<>();
                    Parameter[] parameters = method.getParameters();
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String name = parameter.getAnnotation(Param.class).value();

                        paramValueMapping.put(name, args[i]);
                    }

                    // 解析 SQL 语句中的参数
                    ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
                    GenericTokenParser parser = new GenericTokenParser("#{", "}", tokenHandler);
                    String parseSql = parser.parse(sql);
                    List<ParameterMapping> parameterMappings = tokenHandler.getParameterMappings();

                    // 创建 PreparedStatement 并设置参数
                    PreparedStatement statement = connection.prepareStatement(parseSql);
                    for (int i = 0; i < parameterMappings.size(); i++) {
                        String property = parameterMappings.get(i).getProperty();
                        Object value = paramValueMapping.get(property);
                        Class<?> type = value.getClass();
                        typeHandlerMap.get(type).setParameter(statement, i + 1, value);
                    }

                    // 执行更新操作
                    int rowsAffected = statement.executeUpdate();

                    // 返回更新操作的结果
                    return rowsAffected > 0; // 返回 true 表示更新成功，返回 false 表示更新失败
                } catch (SQLException e) {
                    if (connection != null) {
                        connection.rollback();
                    }
                    throw e;
                }finally {

                    // 还原自动提交状态
                    connection.setAutoCommit(true);
                }
            }

            private Object handleDelete(Connection connection, Method method, Object[] args) throws SQLException {
                // 获取 Delete 注解和 SQL 语句
                Delete annotation = method.getAnnotation(Delete.class);
                String sql = annotation.value();

                // 获取方法参数及其注解
                try {
                    connection.setAutoCommit(false);
                    Map<String, Object> paramValueMapping = new HashMap<>();
                    Parameter[] parameters = method.getParameters();
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String name = parameter.getAnnotation(Param.class).value();

                        paramValueMapping.put(name, args[i]);
                    }

                    // 解析 SQL 语句中的参数
                    ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
                    GenericTokenParser parser = new GenericTokenParser("#{", "}", tokenHandler);
                    String parseSql = parser.parse(sql);
                    List<ParameterMapping> parameterMappings = tokenHandler.getParameterMappings();

                    // 创建 PreparedStatement 并设置参数
                    PreparedStatement statement = connection.prepareStatement(parseSql);
                    for (int i = 0; i < parameterMappings.size(); i++) {
                        String property = parameterMappings.get(i).getProperty();
                        Object value = paramValueMapping.get(property);
                        Class<?> type = value.getClass();
                        typeHandlerMap.get(type).setParameter(statement, i + 1, value);
                    }

                    // 执行删除操作
                    int rowsAffected = statement.executeUpdate();

                    // 返回删除操作的结果

                    return rowsAffected > 0; // 返回 true 表示删除成功，返回 false 表示删除失败
                } catch (SQLException e) {
                    if (connection != null) {
                        connection.rollback();
                    }
                    throw e;
                }finally {

                    // 还原自动提交状态
                    connection.setAutoCommit(true);
                }

            }
        });
        return (T) proxyInstance;
    }
    private static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/qg?characterEncoding=utf-8&allowPublicKeyRetrieval=true&useSSL=false",
                "root", "Wr20050305");
        return connection;
    }
}
