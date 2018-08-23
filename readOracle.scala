package com.dxdbl

import java.sql.{Connection, DriverManager}

import scala.collection.mutable.ArrayBuffer

object readOracle {
  def main(args: Array[String]): Unit = {
    val url = "jdbc:oracle:thin:@192.168.1.35:1521/orcl11g"
    val username = "yq"
    val passwd = "123456"

    val result = ArrayBuffer[String]()

    try{
      // 注册Driver
      classOf[oracle.jdbc.OracleDriver]
      println("开始尝试连接数据库")
      // 连接数据库
      val conn = DriverManager.getConnection(url, username, passwd)
      println("数据库连接成功,执行sql语句")
      // 初始化查询语句
      val statement = conn.createStatement
      // 执行查询结果，并返回结果
      val rs = statement.executeQuery("select t.* from yq.test_m t")
      // 打印返回结果
      var i = 0
      while(rs.next){
        val item = rs.getString("a1")
        result.append(item)
        i = i + 1
      }
      println("查询数据条数：" + result.length)
      println("具体数据如下：")
      var j =1
      for ( x <- result){
        println("第" + j + "条数据为：" + x)
        j += 1
      }
    }
    finally{
      // connection.close
      println("数据查询成功")
    }
  }
}
