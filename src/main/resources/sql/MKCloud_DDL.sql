DROP TABLE IF EXISTS `mkcloud_credit_card_info`;

CREATE TABLE `mkcloud_credit_card_info` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`product_code` varchar(32)  NOT NULL  COMMENT '产品编码',
	`product_name` varchar(200)  NOT NULL  COMMENT '产品名称',
	`institution_id` varchar(32)  NOT NULL  COMMENT '商户id',
	`point_url` varchar(300)  NOT NULL  COMMENT '跳转链接',
	`lable` varchar(32)  NOT NULL  COMMENT '标签',
	`banner_url` varchar(300)  NOT NULL  COMMENT '背景图',
	`all_commission` decimal(18,4)  NOT NULL  COMMENT '总佣金',
	`in_commission` decimal(18,4)  NOT NULL  COMMENT '一级佣金(内)',
	`out_commission` decimal(18,4)  NOT NULL  COMMENT '一级佣金(外)',
	`out_commission_2` decimal(18,4)  NOT NULL  COMMENT '二级佣金',
	`out_commission_3` decimal(18,4)  NOT NULL  COMMENT '三级佣金',
	`order` int(10)  NOT NULL  COMMENT '排序',
	`is_show` int(1)  NOT NULL  DEFAULT '1' COMMENT '是否启用(0:否；1:是)',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='信用卡信息表';

DROP TABLE IF EXISTS `mkcloud_loan_info`;

CREATE TABLE `mkcloud_loan_info` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`product_code` varchar(32)  NOT NULL  COMMENT '产品编码',
	`product_name` varchar(200)  NOT NULL  COMMENT '产品名称',
	`institution_id` varchar(32)  NOT NULL  COMMENT '商户ID',
	`type` varchar(12)  NOT NULL  COMMENT '贷款类型(专业贷；极速贷)',
	`limit_range` varchar(12)  NOT NULL  COMMENT '额度范围',
	`period_range` varchar(12)  NOT NULL  COMMENT '期限范围',
	`point_url` varchar(300)  NOT NULL  COMMENT '跳转链接',
	`lable` varchar(32)  NOT NULL  COMMENT '标签',
	`banner_url` varchar(300)  NOT NULL  COMMENT '背景图',
	`all_commission` decimal(18,4)  NOT NULL  COMMENT '总佣金',
	`in_commission` decimal(18,4)  NOT NULL  COMMENT '一级佣金(内)',
	`out_commission` decimal(18,4)  NOT NULL  COMMENT '一级佣金(外)',
	`out_commission_2` decimal(18,4)  NOT NULL  COMMENT '二级佣金',
	`out_commission_3` decimal(18,4)  NOT NULL  COMMENT '三级佣金',
	`order` int(10)  NOT NULL  COMMENT '排序',
	`is_show` int(1)  NOT NULL  DEFAULT '1' COMMENT '是否启用(0:否；1:是)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='贷款信息表';

DROP TABLE IF EXISTS `mkcloud_manage_user`;

CREATE TABLE `mkcloud_manage_user` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` varchar(200)  NOT NULL  COMMENT '用户id',
	`password` varchar(200)  NOT NULL  COMMENT '密码',
	`status` varchar(1)  NOT NULL  DEFAULT '1' COMMENT '状态(1:有效,0:无效)',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='后管用户表';

DROP TABLE IF EXISTS `mkcloud_sms_send_log`;

CREATE TABLE `mkcloud_sms_send_log` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT  COMMENT '主键',
	`sms_type` varchar(40)  NOT NULL  COMMENT '短信类型、用途(login_bak、changePayPwd)',
	`mobile_num` varchar(20)  NOT NULL  COMMENT '手机号',
	`header` varchar(40)  NOT NULL  COMMENT '短信签名、头',
	`body` varchar(1000)  NOT NULL  COMMENT '短信内容',
	`send_success` int(1)  NOT NULL  DEFAULT '1' COMMENT '发送是否成功',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='短信记录表';

DROP TABLE IF EXISTS `mkcloud_business_information`;

CREATE TABLE `mkcloud_business_information` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`institution_code` varchar(32)  NOT NULL  COMMENT '机构编码',
	`institution_name` varchar(200)  NOT NULL  COMMENT '机构名称',
	`state` varchar(1) NOT NULL COMMENT '合作状态:1合作中,0合作终止',
	`begin_date` datetime NOT NULL COMMENT '合作时间',
	`end_date` datetime  COMMENT '终止时间',
	`create_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`create_user` varchar(32)  COMMENT '创建人',
	`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`update_user` varchar(32)  COMMENT '修改人',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商户信息表';

DROP TABLE IF EXISTS `mkcloud_institution_att`;

CREATE TABLE `mkcloud_institution_att` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`institution_id` bigint(20)   NOT NULL  COMMENT '商户id',
	`institution_code` varchar(20)  NOT NULL  COMMENT '机构编码',
	`attachment_type` varchar(20)  NOT NULL  COMMENT '附件类型',
	`attachment_name` bigint(20)   NOT NULL  COMMENT '附件名称',
	`attachment_address` varchar(20)  NOT NULL  COMMENT '附件地址',
	`attachment_size` int(2)   NOT NULL  COMMENT '附件大小',
	`create_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`create_user` varchar(32)  COMMENT '创建人',
	`update_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`update_user` varchar(32)  COMMENT '修改人',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='机构附件资料表';

DROP TABLE IF EXISTS `mkcloud_business_people`;

CREATE TABLE `mkcloud_business_people` (	
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`business_people_code` varchar(32)  NOT NULL  COMMENT '推广员编码',
	`business_people_name` varchar(200)  NOT NULL  COMMENT '推广员姓名',
	`id_no` varchar(32)  NOT NULL  COMMENT '推广员身份证号',
	`tel` varchar(32)  NOT NULL  COMMENT '推广员手机号',
	`wchat` varchar(32)  NOT NULL  COMMENT '推广员微信号',
	`business_people_type` varchar(12)  NOT NULL  COMMENT '推广员类型',
	`business_people_level` varchar(12)  NOT NULL  COMMENT '推广员级别',
	`province` varchar(12)  NOT NULL  COMMENT '所在省份',
	`city` varchar(12)  NOT NULL  COMMENT '所在市',
	`district` varchar(12)  NOT NULL  COMMENT '所在区县',
	`detailed_address` varchar(300)  NOT NULL  COMMENT '详细地址',
	`state` varchar(1)  NOT NULL  COMMENT '推广员状态',
	`open_id` varchar(200) COMMENT 'OpenID',
	`create_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`create_user` varchar(32)  COMMENT '创建人',
	`update_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`update_user` varchar(32)  COMMENT '修改人',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='推广人员信息表';	

DROP TABLE IF EXISTS `mkcloud_business_people_att`;

CREATE TABLE `mkcloud_business_people_att` (	
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`business_people_id` bigint(20)  NOT NULL  COMMENT '推广员id',
	`attachment_type` varchar(12)  NOT NULL  COMMENT '附件类型',
	`attachment_name` varchar(200)  NOT NULL  COMMENT '附件名称',
	`attachment_address` varchar(200)  NOT NULL  COMMENT '附件地址',
	`attachment_size` varchar(12)  NOT NULL  COMMENT '附件大小',
	`create_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`create_user` varchar(32)  COMMENT '创建人',
	`update_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`update_user` varchar(32)  COMMENT '修改人',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='推广人员资料表';	

DROP TABLE IF EXISTS `mkcloud_business_people_account`;

CREATE TABLE `mkcloud_business_people_account` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`business_people_id` bigint(20)  NOT NULL  COMMENT '推广员id',
	`account_bank` varchar(12)  NOT NULL  COMMENT '推广员银行卡银行',
	`account_code` varchar(32)  NOT NULL  COMMENT '推广员银行卡账号',
	`account_state` varchar(1)  NOT NULL  DEFAULT '1' COMMENT '账号状态:1有效0失效',
	`create_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`create_user` varchar(32)  COMMENT '创建人',
	`update_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`update_user` varchar(32)  COMMENT '修改人',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推广人员银行账号表';

DROP TABLE IF EXISTS `mkcloud_business_relationship`;

CREATE TABLE `mkcloud_business_relationship` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`business_people_id` bigint(20)  NOT NULL  COMMENT '推广员id',
	`business_people_parent_id` bigint(20)  NOT NULL  COMMENT '推广员上级推广员ID',
	`create_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`create_user` varchar(32)  COMMENT '创建人',
	`update_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`update_user` varchar(32)  COMMENT '修改人',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='推广人员关联关系表';

DROP TABLE IF EXISTS `mkcloud_member_info`;

CREATE TABLE `mkcloud_member_info` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_code` varchar(32)  NOT NULL  COMMENT '会员编码',
	`member_name` varchar(200)  NOT NULL  COMMENT '会员姓名',
	`tel` varchar(32)  NOT NULL  COMMENT '会员手机号码',
	`ID_no` varchar(32)  COMMENT '会员身份证号',
	`work_address` varchar(400)  COMMENT '单位地址',
	`channel_code` varchar(32)  NOT NULL  COMMENT '渠道编码',
	`business_people_ID` varchar(32)  COMMENT '推广员ID',
	`business_people_code` varchar(32)  COMMENT '推广员编码',
	`state` varchar(1)  NOT NULL  DEFAULT '1' COMMENT '状态:1有效0失效',
	`open_id` varchar(200) COMMENT 'OpenID',
	`create_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`create_user` varchar(32)  COMMENT '创建人',
	`update_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`update_user` varchar(32)  COMMENT '修改人',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员信息表';

DROP TABLE IF EXISTS `mkcloud_application_import_batch`;

CREATE TABLE `mkcloud_application_import_batch` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`batch_id` varchar(32)  NOT NULL  COMMENT '批次号',
	`counts` varchar(200)  NOT NULL  COMMENT '批次记录总数',
	`state` varchar(32)  NOT NULL  DEFAULT '0' COMMENT '状态:1已确认；0待确认',
	`confirm_time` varchar(32)  NOT NULL  COMMENT '确认时间',
	`confirm_user` varchar(400)  NOT NULL  COMMENT '确认人',
	`create_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`create_user` varchar(32)  COMMENT '创建人',
	`update_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`update_user` varchar(32)  COMMENT '修改人',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='申请记录导入批次';

DROP TABLE IF EXISTS `mkcloud_application_import_detail`;

CREATE TABLE `mkcloud_application_import_detail` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`batch_id` varchar(32)  NOT NULL  COMMENT '批次号',
	`cus_name` varchar(200)  NOT NULL  COMMENT '客户姓名',
	`cus_tel` varchar(32)  NOT NULL  COMMENT '客户手机号',
	`cus_id_no` varchar(32)  COMMENT '客户身份证号',
	`institution_code` varchar(400)  NOT NULL  COMMENT '商户编码',
	`institution_name` datetime  NOT NULL  COMMENT '商户名称',
	`product_name` varchar(32)  NOT NULL  COMMENT '产品名称',
	`feedback_time` datetime  NOT NULL  COMMENT '记录反馈时间',
	`state` varchar(32)  NOT NULL  COMMENT '当前状态',
	`business_people_id` varchar(32)  NOT NULL  COMMENT '关联推荐人ID',
	`create_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`create_user` varchar(32)  COMMENT '创建人',
	`update_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`update_user` varchar(32)  COMMENT '修改人',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='申请记录导入明细';

DROP TABLE IF EXISTS `mkcloud_bank_import_detail`;

CREATE TABLE `mkcloud_bank_import_detail` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`batch_id` varchar(32)  NOT NULL  COMMENT '批次号',
	`apply_id` varchar(32)   COMMENT '申请ID',
	`cus_name` varchar(200)   COMMENT '客户姓名',
	`cus_tel` varchar(32)   COMMENT '客户手机号',
	`cus_id_no` varchar(32)   COMMENT '客户身份证号',
	`institution_code` varchar(200)   COMMENT '商户编码',
	`institution_name` varchar(200)   COMMENT '商户名称',
	`product_name` varchar(200) COMMENT '产品名称',
	`presenter` varchar(200)  COMMENT '推荐者',
	`apply_card_date` varchar(100)   COMMENT '申卡日期',
	`apply_card_time` varchar(100)   COMMENT '申请时间',
	`audit_status` varchar(200)  COMMENT '审核状态',
	`remark` varchar(300)   COMMENT '备注',
	`commission` decimal(18,4)  COMMENT '渠道佣金',
	`in_commission` decimal(18,4)  COMMENT '一级佣金(内)',
	`out_commission` decimal(18,4)  COMMENT '一级佣金(外)',
	`out_commission_2` decimal(18,4)  COMMENT '二级佣金',
	`out_commission_3` decimal(18,4)  COMMENT '三级佣金',
	`business_people_code` varchar(32)  COMMENT '推广员编码',
  `business_people_name` varchar(200)  COMMENT '推广员姓名',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='银行结果记录导入明细表';

DROP TABLE IF EXISTS `mkcloud_bank_import_total`;
CREATE TABLE `mkcloud_bank_import_total` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT  COMMENT '主键',
	`batch_id` varchar(32)  NOT NULL  COMMENT '批次号',
	`counts` int(11)  NOT NULL  COMMENT '批次记录总数',
	`fail_counts` int(11)   COMMENT '失败记录数',
	`fail_reason` varchar(500) COMMENT '失败原因',
	`state` varchar(1)  NOT NULL  COMMENT '状态,1:已确认,0:待确认',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='银行结果记录导入批次表';

DROP TABLE IF EXISTS `mkcloud_commission_detail`;
CREATE TABLE `mkcloud_commission_detail` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`business_people_ID` varchar(32)  COMMENT '推广人员ID',
	`cus_name` varchar(200) COMMENT '客户姓名',
	`cus_tel` varchar(32)  COMMENT '客户手机号',
	`cus_id_no` varchar(32)  COMMENT '客户身份证号',
	`level` varchar(12)   COMMENT '佣金层级',
	`business_people_name` varchar(32)  COMMENT '推广人员姓名',
	`business_people_commission` decimal(18,4)  COMMENT '推广人佣金',
	`state` varchar(1)  COMMENT '佣金状态',
	`detail_id` varchar(32)  COMMENT '原始记录id',
	`batch_id` varchar(32)  COMMENT '批次号',
	`institution_code` varchar(32)  COMMENT '商户编码',
	`institution_name` varchar(400)   COMMENT '商户名称',
	`product_name` varchar(32)  COMMENT '产品名称',
	`approval_date` datetime   COMMENT '审批时间',
	`approval_result` varchar(1)  COMMENT '审批结果',
	`settlement_id` varchar(32)    COMMENT '结佣汇总ID',
	`commission` decimal(18,4)  COMMENT '渠道佣金',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='佣金明细表';


DROP TABLE IF EXISTS `mkcloud_comm_settlement`;
CREATE TABLE `mkcloud_comm_settlement` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT  COMMENT '主键',
	`business_people_ID` varchar(32)  COMMENT '推广人员ID',
	`business_people_name` varchar(32) COMMENT '推广人员姓名',
	`settlement_commission` decimal(18,4)  COMMENT '结算汇总佣金',
	`settlement_id` varchar(32) COMMENT '结佣汇总ID',
	`state` varchar(1)  DEFAULT '0' COMMENT '支付状态:1已支付；0待支付',
	`approval_date` datetime  COMMENT '审批时间',
	`approval_result` varchar(1)  COMMENT '审批结果',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='结佣结算表';

DROP TABLE IF EXISTS `mkcloud_payment_record`;
CREATE TABLE `mkcloud_payment_record` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`cost_type` varchar(12)  COMMENT '费用类型,1:佣金',
	`settlement_id` varchar(32)  COMMENT '佣金结算ID',
	`payable_amount` decimal(18,4)  COMMENT '待支付金额',
	`paid_amount` decimal(18,4) COMMENT '实际支付金额',
	`payee` varchar(32)   COMMENT '收款人姓名',
	`payee_bank` varchar(32)   COMMENT '收款银行',
	`payee_account` varchar(32)  COMMENT '收款账户',
	`state` varchar(1)  COMMENT '支付状态，0：未支付;1:已支付',
	`plan_pay_time` datetime  COMMENT '计划付款日期',
	`batch_code` varchar(32)  COMMENT '支付批次号',
	`confirm_time` datetime   COMMENT '支付确认时间',
	`confirm_user` varchar(32)  COMMENT '支付确认人',
	`paid_bank` varchar(32)   COMMENT '出账银行',
	`paid_acocunt` varchar(32)   COMMENT '出账账户',
	`approval_time` datetime  COMMENT '审批时间',
	`approval_result` varchar(1)  COMMENT '审批结果',
	`commission` decimal(18,4)  COMMENT '渠道佣金',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='支付记录表';








