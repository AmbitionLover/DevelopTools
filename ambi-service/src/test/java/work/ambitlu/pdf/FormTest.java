package work.ambitlu.pdf;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import org.junit.Test;
import org.springframework.util.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * work.ambitlu.pdf
 *
 * @author Ambi 赵帅
 * @Month 11
 */
public class FormTest {

	public static final String FONT_BASE_PATH = "F:\\workpace\\IntelliJ\\Ambi\\qysTools\\ambi-service\\src\\test\\resources\\fonts\\";
	public static final String FILE_PARENT_PATH = "D:\\across\\";

	@Test
	public void TestPdf() throws Exception {

		//test1("me全是宋体已填写.pdf");
		//test1("me全是宋体已填写.pdf");
		//String s1 = fillTextFieldsToPdf("me全是宋体已填写.pdf");
		//test1(s1 + ".pdf");
		//System.out.println("----------------");

		//test1("me全是宋体未填写.pdf");
		//String s3 = fillTextFieldsToPdf("me全是宋体未填写.pdf");
		//test1(s3 + ".pdf");
		//System.out.println("----------------");

		test1("response.pdf");
		//String s = fillTextFieldsToPdf("me创建时加入字体未填写.pdf");
		//test1(s + ".pdf");
		//System.out.println("----------------");
		//
		//test1("9c3f49a6-4465-4ac6-b5c2-4afeda81d136.pdf");
		//String s2 = fillTextFieldsToPdf("me创建时加入字体已填写.pdf");
		//test1(s2 + ".pdf");
		//System.out.println("----------------");

		//test1("me全是宋体已填写.pdf");
		//test1("客户环境大文件.pdf");

	}

	public void test1(String filePath) throws Exception {

		byte[] bytes = File2byte(FILE_PARENT_PATH + filePath);

		PdfReader reader = new PdfReader(bytes);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(reader, baos, '\0', true);
		AcroFields form = stamper.getAcroFields();
		Map<String, AcroFields.Item> fields = form.getFields();

		PRAcroForm acroForm = reader.getAcroForm();
		ArrayList<BaseFont> substitutionFonts = form.getSubstitutionFonts();


		PRIndirectReference prIndirectReference = new PRIndirectReference(reader,33);
		//BaseFont font = BaseFont.createFont(prIndirectReference);

		BaseFont unicode = BaseFont.createFont(FONT_BASE_PATH + "simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED
				, false
				, null
				, null);

		Font font1 = new Font(unicode, 0, Font.NORMAL);

		unicode.setSubset(true);

		createCustomFont(reader, stamper);

		//CMapAwareDocumentFont cMapAwareDocumentFont = new CMapAwareDocumentFont((PRIndirectReference) objref.getIndirectReference());

		for (Map.Entry<String, AcroFields.Item> stringItemEntry : fields.entrySet()) {
			System.out.println(stringItemEntry.getKey());
			AcroFields.Item value = stringItemEntry.getValue();
			System.out.print(value.size() + "    ");
			PdfDictionary value1 = value.getValue(0);
			System.out.print(value1.getAsString(PdfName.DA) + "   ");
			PdfDictionary asDict = value1.getAsDict(PdfName.DR);
			PdfDictionary fonts = asDict.getAsDict(PdfName.FONT);
			if (fonts != null){
				System.out.println(fonts.getKeys());
			}
		}
		// 关闭资源
		stamper.close();
		baos.close();
		reader.close();
	}

	/**
	 * 创建自定义字体
	 * @param reader
	 * @param stamper
	 * @throws IOException
	 */
	@Deprecated
	private void createCustomFont(PdfReader reader, PdfStamper stamper) throws IOException {
		PdfDictionary font2 = new PdfDictionary();
		font2.put(PdfName.BASEFONT,new PdfName("/sss"));
		PRIndirectReference prIndirectReference1 = new PRIndirectReference(reader,37);
		font2.put(PdfName.DESCENDANTFONTS,prIndirectReference1);
		font2.put(PdfName.ENCODING,new PdfName("UniGB-UTF16-H"));
		font2.put(PdfName.NAME,new PdfName("/sss"));
		font2.put(PdfName.SUBTYPE,new PdfName("UniGB-UTF16-H"));
		font2.put(PdfName.TYPE,new PdfName("UniGB-UTF16-H"));


		RandomAccessFile raf = new RandomAccessFile(FONT_BASE_PATH + "simsun.ttf", "r");
		byte fontfile[] = new byte[(int)raf.length()];
		raf.readFully(fontfile);
		raf.close();
		PdfStream stream = new PdfStream(fontfile);
		stream.flateCompress();
		stream.put(PdfName.LENGTH1,
				new PdfNumber(fontfile.length));
		PdfIndirectObject objref
				= stamper.getWriter().addToBody(stream);
		font2.put(PdfName.FONTFILE2,
				objref.getIndirectReference());
	}

	public String fillTextFieldsToPdf(String filename) throws Exception {

		byte[] bytes = File2byte(FILE_PARENT_PATH + filename);
		String id = "text1";

		PdfReader reader = new PdfReader(bytes);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(reader, baos);
		AcroFields form = stamper.getAcroFields();

		PRAcroForm acroForm = reader.getAcroForm();
		//ArrayList<BaseFont> substitutionFonts = form.getSubstitutionFonts();

		// 解析adobe字体  33 是 adobe中文的字体 在pdf中的属性的 序号
		PRIndirectReference prIndirectReference = new PRIndirectReference(reader,33);

		//BaseFont.createFont()

		//BaseFont unicode = BaseFont.createFont("/SimSun", "UniGB-UTF16-H", BaseFont.NOT_EMBEDDED);
		//BaseFont unicode = BaseFont.createFont(prIndirectReference);
		BaseFont unicode = BaseFont.createFont(FONT_BASE_PATH + "simhei.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED
				, true
				, null
				, null);

		// 可替换字体列表
		form.addSubstitutionFont(unicode);

		// 设置字体
		form.setFieldProperty(id,"textfont", unicode,null);
		//form.setFieldProperty("text2","textfont", unicode,null);
		unicode.setSubset(true);

		//merged.get
		boolean success = form.setField(id, null,"哇哈哈哈",true);
		//boolean success1 = form.setField("text2", "哇哈哈哈");
		Assert.isTrue(success,"12312312");
		//Assert.isTrue(success1,"2222");

		// 关闭资源
		stamper.flush();
		stamper.close();
		baos.close();
		reader.close();
		byte[] bytes1 = baos.toByteArray();
		String uuId = UUID.randomUUID().toString();
		System.out.println(uuId);
		byte2File(bytes1,FILE_PARENT_PATH, uuId + ".pdf");
		return uuId;
	}

	public static void byte2File(byte[] buf, String filePath, String fileName)
	{
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try
		{
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory())
			{
				dir.mkdirs();
			}
			file = new File(filePath + File.separator + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(buf);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (bos != null)
			{
				try
				{
					bos.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (fos != null)
			{
				try
				{
					fos.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}


	public static byte[] File2byte(String filePath)
	{
		byte[] buffer = null;
		try
		{
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1)
			{
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return buffer;
	}

}
