play24-slick3-auth-example
=================================

Admin page example with [Play2.4](https://playframework.com/), [Slick3.0](http://slick.typesafe.com/), [play2-auth](https://github.com/t2v/play2-auth).  
Template use [SBAdmin2](http://startbootstrap.com/template-overviews/sb-admin-2/). (exclude Flot charts and Morris.js charts, apply [Bootswatch Slate](https://bootswatch.com/slate/))

Requirements
---------------------------------

* [PostgreSQL](http://postgresapp.com/) install very easy!
* [Bower](http://bower.io/)

Usage
---------------------------------

1. Clone repository
    
    `git clone https://github.com/laughingman7743/play24-slick3-auth-example.git`
    
1. Create database and user
    
    Execute `play24-slick3-auth-example/conf/evolutions/default/create_dtabase_user.sql`    
    (replace YOUR_DATABASE, YOUR_ADMIN_USER, YOUR_USER, YOUR_PASSWORD)
    
1. Fix `play24-slick3-auth-example/conf/application.conf`
    
    Replace database name, user name, password.
    ```
    slick.dbs.default.db.url="jdbc:postgresql://localhost:5432/YOUR_DATABASE"
    slick.dbs.default.db.username=YOUR_USER
    slick.dbs.default.db.password="YOUR_PASSWORD"
    ```
    
1. Install Bower component
    ```
    $ cd play24-slick3-auth-example
    $ bower install
    ```
    
1. Run application
    
    `$ activator run`
    
1. Database evolution
    
    Access to http://localhost:9000/ on your browser and click `Apply this script now!`.
    
1. Login
    
    ```
    email: hoge.fuga@foo.bar
    password: password
    ```

Enjoy!
