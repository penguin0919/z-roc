# 基于java镜像创建新镜像
FROM harbor.bdai.cosmo.com:8888/cosmo_bdai/maven:3.8.4-openjdk-8
#设置时区
ENV TZ="Asia/Shanghai"
# 作者
MAINTAINER wangjunsheng
# 将jar包添加到容器中并更名为app.jar
ADD assistant-server-1.0.0.jar assistant-server-1.0.0.jar
# 运行jar包
ENTRYPOINT ["java","-jar","-Xms1g", "-Xmx1g","-XX:SurvivorRatio=8","-XX:+PrintGCDetails", "-XX:+PrintGCDateStamps","-XX:+HeapDumpOnOutOfMemoryError","-XX:+PrintHeapAtGC","-XX:+PrintReferenceGC","-XX:+PrintTenuringDistribution","-XX:+PrintPromotionFailure","-XX:GCLogFileSize=100M","-XX:+UseGCLogFileRotation","-XX:NumberOfGCLogFiles=14","-XX:HeapDumpPath=/data/crrc-recycling/logs/heap-dump-files/assistant-heapdump.hprof","-Xloggc:/data/crrc-recycling/logs/gc-logs/assistant-gc-%t.log","/assistant-server-1.0.0.jar"]