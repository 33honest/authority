-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: authority
-- ------------------------------------------------------
-- Server version	5.6.27-enterprise-commercial-advanced

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authority_resources`
--

DROP TABLE IF EXISTS `authority_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority_resources` (
  `resource_uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `resource_name` varchar(25) CHARACTER SET utf8 DEFAULT NULL COMMENT '资源名称',
  `resource_platform_uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '资源所在平台uuid',
  `resource_status` int(1) NOT NULL DEFAULT '0' COMMENT '资源状态:\n\n未激活：3\n已激活：1\n已注销：2',
  `resource_url` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '资源链接',
  `resource_level` int(1) NOT NULL COMMENT '资源层级：\n资源分为: 1:系统 ，2:菜单3：菜单下资源。。。\n',
  `resource_order` int(3) NOT NULL COMMENT '资源在所在层级的序号编码',
  `resource_parent_uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '资源父级id',
  `resource_css_code` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '资源css code备用',
  `resource_add_time` varchar(19) CHARACTER SET utf8 NOT NULL COMMENT '资源增加时间\nyyyy-MM-dd HH:mm:ss',
  `resource_add_by` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '资源增加人uuid',
  `resource_del_time` varchar(19) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除时间\nyyyy-MM-dd HH:mm:ss',
  `resource_del_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '删除人uuid',
  `resource__is_delete` int(1) NOT NULL COMMENT '2:未\n1：已删除',
  `resource_edit_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `resource_edit_time` varchar(19) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`resource_uuid`),
  UNIQUE KEY `resource_uuid_UNIQUE` (`resource_uuid`),
  FULLTEXT KEY `resource_name` (`resource_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `authority_role`
--

DROP TABLE IF EXISTS `authority_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority_role` (
  `role_uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `role_name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `role_status` int(1) NOT NULL COMMENT '角色状态:\n\n已添加：1\n已激活: 2\n',
  `role_add_time` varchar(19) CHARACTER SET utf8 NOT NULL COMMENT '添加时间\nyyyy-MM-dd HH:mm:ss',
  `role_add_by` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '添加人uuid',
  `role_del_time` varchar(19) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除时间\nyyyy-MM-dd HH:mm:ss',
  `role_del_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '删除人uuid',
  `role_is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已被删除:\n\n2:未\n1：已被删除',
  `role_platform_uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '平台uuid',
  `role_edit_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `role_edit_time` varchar(19) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`role_uuid`),
  UNIQUE KEY `role_uuid_UNIQUE` (`role_uuid`),
  FULLTEXT KEY `role_uuid` (`role_uuid`,`role_name`,`role_platform_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `authority_role_resources_relation`
--

DROP TABLE IF EXISTS `authority_role_resources_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority_role_resources_relation` (
  `rr_role_uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '角色uuid',
  `rr_resource_uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '资源uuid',
  `rr_uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `rr_add_time` varchar(19) CHARACTER SET utf8 NOT NULL COMMENT '添加时间\nyyyy-MM-dd HH:mm:ss',
  `rr_add_by` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '添加人uuid',
  `rr_del_time` varchar(19) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除时间\nyyyy:mm:dd HH:mm:ss',
  `rr_del_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '删除人uuid',
  `rr_is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已被删除:\n\n2:未\n1：已被删除',
  `rr_grant_type` int(1) NOT NULL COMMENT '授予类型:  \n\n2： 读，写\n1： 读\n\n\n',
  `rr_edit_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `rr_edit_time` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`rr_uuid`),
  UNIQUE KEY `rr_uuid_UNIQUE` (`rr_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色资源关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `authority_user`
--

DROP TABLE IF EXISTS `authority_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority_user` (
  `user_uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `user_login_name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '登录名\n由字母，数字，下划线组合',
  `user_login_pwd` varchar(156) CHARACTER SET utf8 NOT NULL COMMENT '登陆密码',
  `user_email` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '用户邮箱',
  `user_phone` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户电话',
  `user_status` int(1) NOT NULL COMMENT '用户状态:\n\n已注册未激活: 1\n已注册并激活:2\n已锁定:3\n',
  `user_add_time` varchar(19) CHARACTER SET utf8 NOT NULL COMMENT '添加用户时间\nyyyy-MM-dd HH:mm:ss',
  `user_add_by` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '添加用户人(用户主键)',
  `user_del_time` varchar(19) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除用户时间\nyyyy-MM-dd HH:mm:ss\n',
  `user_del_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '删除用户人（用户主键）',
  `user_type` int(1) NOT NULL COMMENT '用户类型：\n\n内部用户:1\n外部用户：2\n',
  `user_add_type` int(1) NOT NULL COMMENT '添加用户类型:\n\n被内部用户添加：1\n\n自己注册:2',
  `user_is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已被删除:\n\n2：未\n1：已被删除',
  `user_edit_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '编辑人',
  `user_edit_time` varchar(19) CHARACTER SET utf8 DEFAULT NULL COMMENT ' 编辑时间',
  PRIMARY KEY (`user_uuid`),
  UNIQUE KEY `user_uuid_UNIQUE` (`user_uuid`),
  FULLTEXT KEY `user_uuid` (`user_uuid`,`user_login_name`,`user_email`,`user_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `authority_user_role_relation`
--

DROP TABLE IF EXISTS `authority_user_role_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority_user_role_relation` (
  `ur_uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `ur_user_uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '用户uuid',
  `ur_role_uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '角色uuid',
  `ur_add_time` varchar(19) CHARACTER SET utf8 NOT NULL COMMENT '增加时间\nyyyy-MM-dd HH:mm:ss',
  `ur_add_by` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '增加人(用户主键)',
  `ur_del_time` varchar(19) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除时间\nyyyy-MM-dd HH:mm:ss',
  `ur_del_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '删除人(用户主键)',
  `ur_is_delete` int(1) NOT NULL COMMENT '是否已被删除\n未：2\n已删除:1',
  `ur_edit_by` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `ur_edit_time` varchar(19) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ur_uuid`),
  UNIQUE KEY `ur_uuid_UNIQUE` (`ur_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `platform`
--

DROP TABLE IF EXISTS `platform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `platform` (
  `platform_uuid` varchar(32) NOT NULL COMMENT '主键',
  `platform_name` varchar(50) NOT NULL COMMENT '平台名',
  `platform_sign` varchar(32) NOT NULL COMMENT '平台标识',
  `platform_domain_name` varchar(30) NOT NULL COMMENT '平台域名',
  `platform_add_time` varchar(19) NOT NULL COMMENT '添加时间\nyyyy-MM-dd HH:mm:ss',
  `platform_add_by` varchar(32) NOT NULL COMMENT '添加人uuid',
  `platform_del_time` varchar(19) DEFAULT NULL COMMENT '删除时间\nyyyy-MM-dd HH:mm:ss',
  `platform_del_by` varchar(32) DEFAULT NULL COMMENT '删除人uuid',
  `platform_status` int(1) NOT NULL COMMENT '平台状态：\n\n初始化: 3\n激活: 1\n注销: 2',
  `platform_is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已被删除:\n0：未\n1：已被删除',
  `platform_edit_time` varchar(19) DEFAULT NULL COMMENT '编辑时间\nyyyyy-mm-dd hh:mm:ss',
  `platform_edit_by` varchar(32) DEFAULT NULL COMMENT '编辑人',
  PRIMARY KEY (`platform_uuid`),
  UNIQUE KEY `template_uuid_UNIQUE` (`platform_uuid`),
  FULLTEXT KEY `platform_uuid` (`platform_uuid`,`platform_name`,`platform_sign`,`platform_domain_name`,`platform_add_time`,`platform_edit_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'authority'
--
/*!50003 DROP PROCEDURE IF EXISTS `createChildList` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `createChildList`(IN rootId VARCHAR(32),IN nDepth INT)
BEGIN
  DECLARE done INT DEFAULT 0;
  DECLARE b VARCHAR(32);
  DECLARE cur1 CURSOR FOR SELECT resource_uuid FROM authority_resources where resource_parent_uuid=rootId;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
  
  insert into treeTmpList values (null,rootId,nDepth);
  SET @@max_sp_recursion_depth = 100;
  
  OPEN cur1;
  
  FETCH cur1 INTO b;
  WHILE done = 0 DO
      CALL createChildList(b,nDepth+1);
      FETCH cur1 INTO b;
  END WHILE;
  
  CLOSE cur1;
  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `showChildList` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `showChildList`(IN rootId VARCHAR(32))
BEGIN
  CREATE TEMPORARY TABLE IF NOT EXISTS treeTmpList
  (treeid int primary key auto_increment,id VARCHAR(32),depth int);
  DELETE FROM treeTmpList;
  
  CALL createChildList(rootId,0);
  
  select resource.* from treeTmpList tree,authority_resources resource where tree.id=resource.resource_uuid order by tree.treeid;
  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-05 15:17:01
