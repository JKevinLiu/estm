INSERT INTO `t_audit` VALUES (1001, '249520181017142030', 1, 1015, NULL, 3, '2018-10-18 14:20:23', '2018-10-18 16:20:10', 'audit/249520181017142030', '要得！');
INSERT INTO `t_audit` VALUES (1002, '249520181018142030', 1, 1016, NULL, 1, '2018-10-18 14:20:23', NULL, 'audit/249520181018142030', '');
INSERT INTO `t_audit` VALUES (1003, '249520181019142030', 1, 1017, NULL, 1, '2018-10-18 14:20:23', NULL, 'audit/249520181019142030', '');

INSERT INTO `t_audit_item` VALUES (1001, '249520181017142030', 1, 1, 'reqcert.doc', 'audit/249520181017142030/');
INSERT INTO `t_audit_item` VALUES (1002, '249520181017142030', 4, 2, '1.jpg', 'audit/249520181017142030/card/');
INSERT INTO `t_audit_item` VALUES (1003, '249520181017142030', 4, 2, '2.jpg', 'audit/249520181017142030/card/');
INSERT INTO `t_audit_item` VALUES (1004, '249520181017142030', 6, 2, '3.jpg', 'audit/249520181017142030/contract/');
INSERT INTO `t_audit_item` VALUES (1005, '249520181017142030', 6, 2, '4.jpg', 'audit/249520181017142030/contract/');

INSERT INTO `t_inner_user` VALUES (1001, 'admin', '123456', '管理员', '2018-10-18 11:10:34', NULL);

INSERT INTO `t_out_user` VALUES (1015, '张三', '18285192495', '1234567890', '2018-10-17 10:49:45');
INSERT INTO `t_out_user` VALUES (1016, '李四', '18285192496', '1234567891', '2018-10-18 10:49:45');
INSERT INTO `t_out_user` VALUES (1017, '王五', '18285192497', '1234567892', '2018-10-19 10:49:45');

INSERT INTO `t_wx_send` VALUES (1, 1001, 1015, '要得！', '2018-10-18 16:16:24', '2017-12-31 10:00:00', NULL);