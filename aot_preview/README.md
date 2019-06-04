# 不重複username 註冊申請機制 -bryant

* 環境需求\(java 8 , docker , maven \)
* 建議系統\(ubuntu, mac os ,cent os\)

  1.需安裝 docker  check versions. 可參考

  > [https://docs.docker.com/](https://docs.docker.com/)

  ```
  $ docker --version
  Docker version 18.09, build c97c6d6
  ```

  2.需安裝 maven  check versions. 可參考

  > [http://maven.apache.org/install.html](http://maven.apache.org/install.html)

  ```
  $ mvn -v
  ```

* 安裝方式

  專案名稱：aot-spring-boot-jpa-docker

  > github : [https://github.com/kings7788/aot-spring-boot-jpa-docker.git](https://github.com/kings7788/aot-spring-boot-jpa-docker.git)

  1.先把專案pull到任意資料夾  
  2.在terminal下docker指令 先執行一個mysql的container

  ```
    docker run -d \
    -p 2012:3306 \
   --name mysql-docker-container \
   -e MYSQL_ROOT_PASSWORD=root123 \
   -e MYSQL_DATABASE=spring_app_db \
   -e MYSQL_USER=app_user \
   -e MYSQL_PASSWORD=test123 \
      mysql:latest
  ```

  3.在專案的根目錄輸入
  
  > ex.adminde-Mac-mini:aot\_preview root\# mvn clean install -DskipTests
  
  ```
  $ mvn clean install -DskipTests
  ```
  
  4.確認jar 包 bulid success 之後,在專案的根目錄下輸入
  
  ```
  $ docker build -f Dockerfile -t spring-jpa-app .
  ```
  
  5.此時輸入docker images 應該會看到一個 spring-jpa-app 的映像
  
  6.接著輸入
  
  ```
  $ docker run -t --name spring-jpa-app-container --link mysql-docker-container:mysql -p 8087:8080 spring-jpa-app
  ```
  
  7.spring-boot的container 會走起來 可在瀏覽器輸入url做測試
  
  ```
  http://localhost:8087/aot/index
  ```
  
  8.也可以用post man 測試
  
  ![](/assets/test-api-user.png)
  


