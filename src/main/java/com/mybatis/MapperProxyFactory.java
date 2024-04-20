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



                Connection connection =getConnection();

                Select annotation = method.getAnnotation(Select.class);
                String sql =annotation.value();//获取注解

                //拿到入参数
                Map<String,Object> paramValueMapping =new HashMap<>();
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    Parameter  parameter=parameters[i];
                    String name = parameter.getAnnotation(Param.class).value();
                    paramValueMapping.put(parameter.getName(),args[i]);
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


                connection.close();
                if (!list.isEmpty()) {
                    if (method.getReturnType().equals(List.class)) {
                        result = list;
                    } else {
                        result = list.get(0);
                    }
                } else {
                    return null;
                }

                return result;
            }
        });
        return (T)proxyInstance;
        }
    private static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/qg?characterEncoding=utf-8&allowPublicKeyRetrieval=true&useSSL=false",
                "root", "Wr20050305");
        return connection;
    }
}
