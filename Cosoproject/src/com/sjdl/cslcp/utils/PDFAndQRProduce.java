package com.sjdl.cslcp.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/** 
* @author 作者 张鑫博:
* @version 创建时间：2021年7月2日 上午10:11:32 
*/

public class PDFAndQRProduce {

	/**
	 * pdf生成工具类
	 * @param map所有数据
	 * @param pdfinpath pdf模板路径
	 * @param pdfoutpath pdf生成路径
	 * @param imagePath logo文件所在路径
	 * @return 无返回
	 */
	public static void paqrprod(Map<String, Object> map, String pdfinpath, String pdfoutpath, String imagePath,String signaturePath,String sendSealPath) {
		String outPath = "D:\\upload\\QRcode\\";
		
		
		try {
			String content = (String) map.get("blNo");
			// 生成二维码
			// 参数放到map中
			HashMap ha = new HashMap();
			// 编码格式
			ha.put(EncodeHintType.CHARACTER_SET, "utf-8");
			// 纠错等级【L，M，Q，H】
			ha.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
			ha.put(EncodeHintType.MARGIN, 2);
			// 依赖方法 参数为
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 300, 300, ha);
			// 文件写出
			Path file = new File(outPath).toPath();
			// 写出 @参数
			MatrixToImageWriter.writeToPath(bitMatrix, "png", file);
			// 图片域名称
			String fieldName = "blNoimage";
			String fieldName1 = "agent";
			String fieldName2 = "carrier";
			// 二维码图片域名称
			String fieldCodename = "QRcode";
			PdfReader reader;
			FileOutputStream out;
			ByteArrayOutputStream bos;
			PdfStamper stamper;
			out = new FileOutputStream(pdfoutpath);// 输出流
			reader = new PdfReader(pdfinpath);// 读取pdf模板
			bos = new ByteArrayOutputStream();
			stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();
			BaseFont chinessFont = BaseFont.createFont("C:\\Windows\\Fonts\\STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			form.addSubstitutionFont(chinessFont);
			form.setField("blNo", content);
			form.setField("shipper", (String) map.get("shipper"));
			form.setField("consignee", (String) map.get("consignee"));
			form.setField("fordelivery", (String) map.get("fordelivery"));
			form.setField("notifyParty", (String) map.get("notifyParty"));
			form.setField("placeOfreceipt", (String) map.get("placeOfreceipt"));
			form.setField("preCarriageBy", (String) map.get("preCarriageBy"));
			form.setField("typeOfMovement", (String) map.get("typeOfMovement"));
			form.setField("NoOfOriginal", (String) map.get("NoOfOriginal"));
			form.setField("vessel", (String) map.get("vessel"));
			form.setField("voyageNo", (String) map.get("voyageNo"));
			System.out.println("****************************************");
			System.out.println(map.get("portofLoading"));
			System.out.println("****************************************");
			form.setField("portOfLoading", map.get("portofLoading").toString());
			form.setField("portOfTransshipment", (String) map.get("portOfTransshipment"));
			form.setField("PortOfDischarge", (String) map.get("PortOfDischarge"));
			form.setField("PlaceOfDelivery", (String) map.get("PlaceOfDelivery"));
			form.setField("finalDestination", (String) map.get("finalDestination"));
			form.setField("freightCharges", (String) map.get("freightCharges"));
			form.setField("declared", (String) map.get("declared"));
			form.setField("placeAndDate", (String) map.get("placeAndDate"));
			form.setField("totalNumber", (String) map.get("totalNumber"));
			
			
			// 通过域名获取所在页和坐标，左下角为起点
			int pageNo = form.getFieldPositions(fieldName).get(0).page;
			Rectangle signRect = form.getFieldPositions(fieldName).get(0).position;
			float x = signRect.getLeft();
			float y = signRect.getBottom();
			// 读图片
			Image image = Image.getInstance(imagePath);
			
			// 获取操作的页面
			PdfContentByte under = stamper.getOverContent(pageNo);
			// 根据域的大小缩放图片
			image.scaleToFit(signRect.getWidth(), signRect.getHeight());
			// 添加图片
			image.setAbsolutePosition(x, y);
			under.addImage(image);
			// 添加二维码
			// 通过域名获取所在页和坐标，左下角为起点
			int codeNo = form.getFieldPositions(fieldCodename).get(0).page;
			Rectangle signRection = form.getFieldPositions(fieldCodename).get(0).position;
			float a = signRection.getLeft();
			float b = signRection.getBottom();
			// 读图片
			Image images = Image.getInstance(outPath);
			// 获取操作的页面
			PdfContentByte unders = stamper.getOverContent(codeNo);
			// 根据域的大小缩放图片
			images.scaleToFit(signRection.getWidth(), signRection.getHeight());
			// 添加图片
			images.setAbsolutePosition(a, b);
			unders.addImage(images);	
			//agent签章
			// 通过域名获取所在页和坐标，左下角为起点
			int agentNo = form.getFieldPositions(fieldName1).get(0).page;
			Rectangle signRect1 = form.getFieldPositions(fieldName1).get(0).position;
			float c = signRect1.getLeft();
			float d = signRect1.getBottom();
			// 读图片
			Image image1 = Image.getInstance(sendSealPath);
			// 获取操作的页面
			PdfContentByte under1 = stamper.getOverContent(agentNo);
			// 根据域的大小缩放图片
			image1.scaleToFit(signRect1.getWidth(), signRect1.getHeight());
			// 添加图片
			image1.setAbsolutePosition(c, d);
			under1.addImage(image1);
			//carrer章
			int carrerNo = form.getFieldPositions(fieldName2).get(0).page;
			Rectangle signRect2 = form.getFieldPositions(fieldName2).get(0).position;
			float e = signRect2.getLeft();
			float f = signRect2.getBottom();
			// 读图片
			Image image2 = Image.getInstance(sendSealPath);
			// 获取操作的页面
			PdfContentByte under2 = stamper.getOverContent(carrerNo);
			// 根据域的大小缩放图片
			image2.scaleToFit(signRect2.getWidth(), signRect2.getHeight());
			// 添加图片
			image2.setAbsolutePosition(e, f);
			under2.addImage(image2);
			
			stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
			stamper.close();
			Document doc = new Document();
			PdfCopy copy = new PdfCopy(doc, out);
			doc.open();
			PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
			copy.addPage(importPage);
			doc.close();
			
		} catch (IOException e) {
			System.out.println(1);
		} catch (DocumentException e) {
			System.out.println(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
