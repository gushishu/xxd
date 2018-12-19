package com.xxd.utils;

import java.math.BigDecimal;

/**
 * 积分的相关规则
 * @author Liang
 * @version 1.0
 */

public class IntegrationU {
	
	//平台利润
	public static final BigDecimal PLATFORMPROFIT = new BigDecimal(0.2);
	
	//积分冻结时间
	public static final Long INTEGRATIONFREEZETIME = new Long(3);
	
	public static BigDecimal addition(BigDecimal bd1, BigDecimal bd2) {
		return bd1.divide(bd2);
	}

}
