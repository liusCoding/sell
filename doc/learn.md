联调：
本机hosts文件：虚拟机ip sell.com

虚拟机nginx：server_name  sell.com


前端项目路径：/opt/code/sell_fe_buyer/config/

构建命令：npm run bulid 

将构建好的文件拷贝到/opt/data/wwwroot/sell/

cp -r dist/* /opt/data/wwwroot/sell/

解决方案：

1、 删除所有以.lastUpdate结尾的文件

a) 1、切换到maven的本地仓库

b) 2、在当前目录打开cmd命令行

c) 3、执行命令：for /r %i in (*.lastUpdated) do del %i


## 第七讲

- 微信授权
- 微信支付
- 微信退款


获取openId

两种方式
- 手工方式
- 利用第三方sdk



## 工具

内网穿透

https://natapp.cn


   <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>



        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions>

        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.4</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
        </dependency>

        <dependency>
            <groupId>com.github.binarywang</groupId>
            <artifactId>weixin-java-mp</artifactId>
            <version>3.5.0</version>
        </dependency>


