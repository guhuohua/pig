FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /pigx-goods

WORKDIR /pigx-goods

EXPOSE 4000

ADD ./pig-goods/pig-goods-manage/target/pig-goods-manage.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar pig-goods-manage.jar
