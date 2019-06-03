不重複username 註冊申請機制 -bryant
===============

* 安裝方式

>aot-spring-boot-jpa-docker 
>github : https://github.com/kings7788/aot-spring-boot-jpa-docker.git

 1.先把專案pull到任意資料夾
 2.在linux 環境下docker指令 先執行一個mysql的container

      docker run -d \
      -p 2012:3306 \
     --name mysql-docker-container \
     -e MYSQL_ROOT_PASSWORD=root123 \
     -e MYSQL_DATABASE=spring_app_db \
     -e MYSQL_USER=app_user \
     -e MYSQL_PASSWORD=test123 \
        mysql:latest


 3.在專案的根目錄 輸入  

	
       mvn clean install -DskipTests

   
  

 4.確認jar 包 bulid success 之後,在專案的根目錄下輸入


       docker build -f Dockerfile -t spring-jpa-app .



 5.此時輸入docker images 應該會看到一個 spring-jpa-app 的映像


 6. 接著輸入


       docker run -t --name spring-jpa-app-container --link mysql-docker-container:mysql -p 8087:8080 spring-jpa-app


 7. spring-boot的container 會走起來 可在瀏覽器輸入url做測試

       
       localhost:8087/aot/index



