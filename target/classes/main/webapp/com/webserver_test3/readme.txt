6.0版本 ：
从本版本开始，服务端开始支持处理业务的操作。例如用户注册，登录等操作

以注册为例，流程大致为：
1.用户打开注册页面
2.在页面上输入注册信息
3.点击注册按钮
4.数据提交到服务端
5.服务端将数据保存
6.响应注册结果页面(注册成功或失败页面)

本版本完成内容：
1.创建注册页面，并理解form表单应用
2.服务端HttpRequest可以解析用户提交的表单数据

实现：
1.在webapps/myweb目录下新建注册页面：reg.html
2.重构HttpRequest解析请求的操作
   由于reg.html页面以GET形式提交了一个表单，这时请求的请求行中URL部分的内容变为了如下样子：
   /myweb/reg?username=xxx&password=xxx&...
   即：请求的抽象路径中包含了用户传递过来的参数。
   因此我们对抽象路径部分要做进一步的解析工作。

   在HttpRequest中再定义三个属性：
   StringrequestURI 保存url中"?"左侧的请求部分
   String queryString 保存url中"?"右侧的参数部分
   Map parameters  保存解析出来的每一个参数
   创建一个parseURL，用来进一步解析请求行中抽象路径url的内容