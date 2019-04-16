2.0版本主要工作：创建一个网络应用中所需要的测试页面

WebServer是一个网络容器，管理着多个网络应用(WebApp)
每一个网络应用都应当包含(页面，静态资源，业务代码等)，因此我们在文本server项目目录下新建一个目录webapps，
用这个目录保存所有的网络应用，每个网络应用都保存在webapps下的一个单独目录中，而该目录的名字就是这个网络应用的名字。

实现：
1.在webServer项目目录下新建一个目录webapps
2.在webapps目录下新建一个子目录：myweb
3.在myweb目录下新建第一个页面：index.html
4.完成ClientHandler 中处理客户端请求的第二步工作：
处理请求
由于我们将每个网络应用都放在了webapps目录下了，因此浏览器输入url地址时的资源抽象路径所对应的目录。应当就是从webapps下开始的。
例如：
http://localhost:8088/myweb/index.html
那么我们在解析请求行中抽象路径得到的部分就是： /myweb/index.html
这时我们从webapps目录下根据抽象路径应当就可以找到该文件了。



