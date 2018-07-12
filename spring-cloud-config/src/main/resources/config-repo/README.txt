配置规则详解

classpath:/config-repo目录作为配置仓库

访问配置信息的URL与配置文件的映射关系如下:

/{application}/{profile}
/{application}-{profile}.properties

例如 http://localhost:8888/provider-server/db、http://localhost:8888/provider-server-db.properties