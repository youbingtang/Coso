package com.sjdl.cslcp.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.sjdl.cslcp.util.AlipayConfig;

/** 

* @author 作者 张鑫博: 

* @version 创建时间：2021年7月13日 下午6:10:35 

*/
@RestController
public class AlipayController {
	
	@RequestMapping("/alipay")
	public String alipay(String money,String blNo) {
		

		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = blNo;
		//付款金额，必填
		String total_amount = money;
		//订单名称，必填
		String subject = "当前账户支付订单";
		//商品描述，可空
		String body = "没啥描述的";
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		//		+ "\"total_amount\":\""+ total_amount +"\"," 
		//		+ "\"subject\":\""+ subject +"\"," 
		//		+ "\"body\":\""+ body +"\"," 
		//		+ "\"timeout_express\":\"10m\"," 
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
		
		//请求
		String result = "";
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
			System.out.println("-------------");	
			//输出
		System.out.println(result);
		
		
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		
		return result;
		
		/*
		 * Map<String,String> params = new HashMap<String,String>();
		 * Map<String,String[]> requestParams = request.getParameterMap(); for
		 * (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();)
		 * { String name = (String) iter.next(); String[] values = (String[])
		 * requestParams.get(name); String valueStr = ""; for (int i = 0; i <
		 * values.length; i++) { valueStr = (i == values.length - 1) ? valueStr +
		 * values[i] : valueStr + values[i] + ","; } //乱码解决，这段代码在出现乱码时使用 valueStr = new
		 * String(valueStr.getBytes("ISO-8859-1"), "utf-8"); params.put(name, valueStr);
		 * }
		 * 
		 * boolean signVerified = AlipaySignature.rsaCheckV1(params,
		 * AlipayConfig.alipay_public_key, AlipayConfig.charset,
		 * AlipayConfig.sign_type); //调用SDK验证签名 if(signVerified) {
		 * 
		 * 
		 * }else { System.out.println("验签失败"); }
		 */
		
		
	}
	
	/*
	 * @RequestMapping("/apppay") public apppay(String out_trade_no,String trade_no
	 * ,String total_amount) { Map<String,String> params = new
	 * HashMap<String,String>(); Map<String,String[]> requestParams =
	 * request.getParameterMap(); for (Iterator<String> iter =
	 * requestParams.keySet().iterator(); iter.hasNext();) { String name = (String)
	 * iter.next(); String[] values = (String[]) requestParams.get(name); String
	 * valueStr = ""; for (int i = 0; i < values.length; i++) { valueStr = (i ==
	 * values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ","; }
	 * //乱码解决，这段代码在出现乱码时使用 valueStr = new String(valueStr.getBytes("ISO-8859-1"),
	 * "utf-8"); params.put(name, valueStr); }
	 * 
	 * boolean signVerified = AlipaySignature.rsaCheckV1(params,
	 * AlipayConfig.alipay_public_key, AlipayConfig.charset,
	 * AlipayConfig.sign_type); //调用SDK验证签名
	 * 
	 * //——请在这里编写您的程序（以下代码仅作参考）—— if(signVerified) { //商户订单号 String out_trade_no =
	 * new
	 * String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
	 * 
	 * //支付宝交易号 String trade_no = new
	 * String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
	 * 
	 * //付款金额 String total_amount = new
	 * String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
	 * 
	 * out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+
	 * "<br/>total_amount:"+total_amount); }else { out.println("验签失败"); } }
	 */

	
}
