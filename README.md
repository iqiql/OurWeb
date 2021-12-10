# OurWeb

## 功能说明
* 留言，留言需要留言者的称呼，联系方式，需求描述（此项可以不填）
* 如果留言者的称呼或联系方式为空或者为全为空格，弹出弹窗提示用户
* 留言将提交至我的服务器的MySQL数据库（ip：47.96.149.48）同时，以我的QQ邮箱为名义将留言者的留言信息发送至邮箱 qiqilong1043@163.com （我的网易邮箱，可以修改为任意邮箱，也可以同时发送到很多个邮箱，但是暂时只发到了我的邮箱）
* 网站用户留言不做展示
* 网站除了首页（index.html）以及Contract（联系我们）那部分需要放置留言板以外，其他部分不需要放置留言板

## 所采用的的框架：
* 网站后端使用了springboot2.6.2、mybatis、SpringMVC框架、MySQL数据库版本为8.0.26
## 目录说明
![image](https://user-images.githubusercontent.com/94536241/145499743-448f446c-3a09-45ae-85a6-e4a644bdfb95.png)
> 从创建时间开始介绍吧
* 设计数据库，数据库的文件位于src/resources/ 目录下的db.sql中
* 根据数据库创建实体类Message，实体类位于src/main/java/com/benyuantech/challenge/model/目录下，这里用于@Data注解，此注解需要添加lombok依赖，需要在pom.xml文件中的depenencys标签下添加：
~~~xml
<!--lombok依赖-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
~~~
* 创建服务层，即service包，然后由于只设计到留言服务，故创建了MessageService接口，此接口中应该定义对留言的存储方法
* 接口需要实现类，故创建了impl包创建了MessageServiceImpl类以实现MessageService接口，此类中的方法也不是具体的留言实现，他需要调用持久层的方法
* 创建持久层，即mapper包，并创建了MessageMapper接口定义了操作数据库的方法，我在里面写了两个方法，insert和insertSelective方法，通过查看两个方法的mybatis语句可以发现，后者需要进行很多次的判断，保证不会插入字段为空的sql语句，但是也会拖慢程序速度，所以程序实际上只是运行的第一个方法，调用第一个方法需要保证插入的数据都不为空，否则将报错，这一点我在控制层进行了处理，保证均不为空，所以可以大胆调用。此接口的代理类由springboot创建。
* 创建mapper文件，即 MessageMapper.xml文件，由于个人习惯问题，我更喜欢将mapper文件与接口放在一起，此文件中将生成业务中具体功能的sql语句。
* 创建控制层，即Controller包，业务简单，所以只需要创建一个MessageController类，暴露给前段的请求接口为 add/message 带三个参数（实际上应该是四个，另一个是请求头）：昵称、联系方式、留言内容。根据代码可以看到，我还封装了留言者的ip到实体类中，以及留言者的留言时间。
* 创建工具类，即util包，该包下有两个类IpUtil类用来获取请求头的ip（真实ip而非多级代理ip），EmailUtil用来提供邮件服务
* 邮件的实现在服务层下的代理类中，即MessageServiceImpl类
* resources/static/目录下将用来放置之后的所有前段代码，因为网站没有数据交互（只上传留言不需要在网站上显示留言，所以html是静态的不需要动态生成），所以templates目录下无需放置文件
> 目录结构到此就差不多了，其他目录，像test不用去管，写测试类用的
