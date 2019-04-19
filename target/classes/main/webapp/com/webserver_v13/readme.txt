9.0主要版本主要工作：重构代码

不同的业务我们会定义相应的Servlet来处理，因此我们要规范一下所有Servlet，提供一个超类，并在
超类中定义所有Servlet都会使用的功能。

实现：
1：在com.webserver.servlet包中定义类:HttpServlet
该类是一个抽象类

2：在HttpServlet中定义抽象方法：service，用于规定所有的Servlet都必须具备该方法

3：使现有的Servlet都继承HttpServlet