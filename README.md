# spring_mybatis_atomikos_tomcat
Spring4.3.6 + atomikos3.9.0 + mybatis3.4.6+mybatis-spring1.3.0搭建分布式事务demo

# pom文件配置Tomcat插件，发布项目到tomcat中
<!-- 配置Tomcat插件 -->
			<plugin>
				<groupId>org.apache.tomcat.maven</groupId>
				<artifactId>tomcat7-maven-plugin</artifactId>
				<version>2.2</version>
				<configuration>
					<!-- 一般eclipse启动项目时候这里配置什么端口，访问项目的时候就是什么端口；用了热部署后， 是部署到目标tomcat里，因此这个port算是没用，访问时，是在tomcat的端口 -->
					<port>8081</port>
					<!-- 部署到ROOT下/test -->
					<path>/test</path>
					<!-- tomcat的地址和端口，manager/text是固定的 -->
					<url>http://127.0.0.1:8080/manager/text</url>
					<username>tomcat</username>
					<password>tomcat</password>
				</configuration>
			</plugin>
# 测试接口http://127.0.0.1:8080/test/addName?hasException=true
