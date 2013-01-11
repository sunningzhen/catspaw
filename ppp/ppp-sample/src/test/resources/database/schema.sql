-- ----------------------------
-- Table structure for smp_attention
-- ----------------------------
CREATE TABLE smp_attention (
  trading_date date DEFAULT NULL,
  secucode varchar(6) DEFAULT NULL,
  stall varchar(2) DEFAULT NULL,
  busi_code int(1) DEFAULT NULL,
  volume int(11) DEFAULT NULL,
  amount decimal(10,0) DEFAULT NULL,
  cust_num int(11) DEFAULT NULL
);