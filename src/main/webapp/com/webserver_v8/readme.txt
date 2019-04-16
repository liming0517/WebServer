5.0版本 ： 重构HttpResponse发送响应头的工作

之前版本中HttpResponse发送响应头的方法：sendHeaders是发送两个固定的头：Content-Type和Content-Length。甚至Content-Type的值
是写死的，而不是根据客户端实际请求的资源类型而改变的。这导致我们显示学子商城这套页面时出现显示不正常的情况。

因此我们对发送响应头的工作作如下两个改动：
1.发送响应头改为根据实际设置的头进行发送，而不是固定发送两个头。
2.对于Content-Type的值应当根据请求的资源而决定它对应的值，以便浏览器可以正确理解其请求的资源进行显示。


实现：
1.在HttpResponse中定义一个属性：Map hearders
   其中key表示响应头名字，value表示响应头的值
2.修改发送响应头的方法：sendHeards，将原有固定发送改为通过遍历hearders发送所有响应头

3.对外提供putHeader方法，一边外界可以对HttpResponse 设置要发送的响应头。

4.在com.webserver.http包中定义一个类：HttpContext
这个类用于将所有有关HTTP协议规定的内容定义在这里，本次先定义一个静态属性Map mimeMapping，用来记录
资源后缀与Content-Type值的对应，然后在静态块中初始化这个Map。
最后定义一个静态方法：getMimeType，用于让外界可以根据给定的资源后缀获取到对应的Content-Type的值

5.ClientHandler在处理请求中若找到了资源，在向响应对象设置完该资源到正文中后，再设置其对应的两个响应头，其中Content-Type
对应的值可以根据该资源的后缀从HttpContext找到
没有找到资源则设置响应头与原来一致。
当以上操作完成后，学子商城页面可以正常显示。