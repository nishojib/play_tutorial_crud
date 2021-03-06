name := """demo_tutorial"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(guice, javaWs, ehcache)

//libraryDependencies += guice
//libraryDependencies += javaWs
//libraryDependencies += ehcache