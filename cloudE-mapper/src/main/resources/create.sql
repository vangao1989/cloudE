CREATE TABLE `user` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL COMMENT '<p>用户名</p>不允许重复，要求应用层不允许重复',
  `password` varchar(32) NOT NULL COMMENT '<p>用户密码</p>',
  `status` tinyint(4) DEFAULT NULL COMMENT '<p>用户状态</p>',
  `reg_login_ip` varchar(32) DEFAULT NULL COMMENT '<p>用户注册时的ip地址</p>',
  `last_login_ip` varchar(32) DEFAULT NULL COMMENT '<p>最后登陆的ip</p>',
  `last_login_time` datetime DEFAULT NULL COMMENT '<p>最后登陆的时间</p>',
  `create_time` datetime NOT NULL COMMENT '<p>用户创建日期</p>系统时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '<p>最后修改日期</p>系统时间',
  `del_status` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12244 DEFAULT CHARSET=utf8 COMMENT='<p>用户基本信息表</p>'