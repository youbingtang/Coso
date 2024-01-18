package com.sjdl.cslcp.util;
/** 

* @author 作者 张鑫博: 

* @version 创建时间：2021年7月13日 下午4:21:39 

*/
public class AlipayConfig {
	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000117681794";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCzJv0Z8Gi7ZXAQADVc0VFqsXUXYhFyTRwwFAymHXdWf03D3QNGYFSWpsL7P/acpJj65yy40vgltG8+NLFwmHufA5SACQrvUpHNVwgL7j3vtoQfbtc0dccxW/ty38tG8piDkhLm00EhPjQpptttKLmDV67L3LPKTD7Tk5ra7+QGWmrl/17yo7XJ3U+RDQnjvgW24y9Th7FMABRtJ0HQ5WJm20QwkrIuL5Pbo6GvBMrJaZXALOXL4n1+lyCSV2jQadNwwGLE/rbU2vSL21/XKYXsu8p3ljHfeSrHVUxDQ1q2MRzrQpWua4iQYv31NLhgSt3lrizRFLfisw1vL6GQFD/1AgMBAAECggEBAIZRhRIdnWhi9VIJ1fhxN4m24wNVE5+PEMM7RQCGtyKy76sZ2Yq6TrFk9mtJ2aw3/DqheHZaNpKOZZlOCHtS19yw4nXcUcZ9MbOyvOW1CdrgDM9EWzWJ2H65aB0IjW/0E0USD/ngCcTSzmksIPgGwJAo/XT7VBEoeTJISKI9mcjoXktQ7WVNTR/ejVY0Ps/GxLGXpLcrRkax2/Zm8jT6r3qORP4qyNtUHA9RN1jF/S3A6OwD5Vgwhz2tmasY8IyeoLwCaBTik6qak823uk3e+EO4mVvIVzEt/GuVuOP2/5pzmq5MtQuHk1ZxFPs1YAS3km/038maP3dP8g+1Ehan6AECgYEA8KzGZOqlLgJEktswFvsSuJ4yV9gb/17ZRWCqtKHDUnLXbZQ/ZOs/H7rJ6j1OBbIGLf4UVg2tGw6L9v48KBkK3t/WDm6ZMCTg1il7GFSPQ0BzUgaY6PNLhctE3Ez4/0txkFjTEpdl4SrIi+ubByL1+ThIWG1DPRmXrnCmG9vS9OECgYEAvo9WhjYJcB67GKHZK+gOehFKcTR7IUkjoJLuizRmpQ7JElC39vOYjZ5nz7vAffcjRC3lybLsnSH+NAlhjGDuLrI+QIsGXgWuRNqFlUGd0B8mOXs734Fd1t4ga1Onnm5mPklfkqKrIV0T15cCVag+WUJof57JA1JnmNknkHrZ2ZUCgYAdks1qFx8m8SZl+UMGf+GECARdoPOHxKrkCIXRXlM27V+lWmMTzohaKMSo4NGWh5KU0jTDgji0K2xtdD4GG1Wnn0+ZRGPUc6OhDUXP7owQEnpZKPBfJ44vKZQTke3CyQ1yVEfTcAEX0v8DmnbZQKWC8zSIkRyDBIML71Gp+LAKYQKBgQCPedzrsEPq5zh3k2Swakh8IAiss5cBuM/aVsbEiO+/1yINaDka9s4l0t7WycZlEW68JtKcytrcdHuxMl8AEvtCrXGxpvtkT2Km9OEfQAhSayGcpeyum/jLjvNZ2+KYpwbUXupWvaObuzj1Wf2oAMTw788+R4+RsLnPm7DuV5EvAQKBgQDpmIENujVD4mKXzOThr2ZvU3a6o7RQU/zZCHk8tXooL7qeh4ELRTEuGpt5ADo3NiFwL/Su0K4HQxWhhMEpL/D/9Bbh6DtMFpp+gL2zNVqWpBVCAKxgZzy8rz3UHUkq059jjOqHZQuZ4MvZ60zCUMAx90ItSOsYCQ5EeBNXwwH5LA==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/kong";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";
}
