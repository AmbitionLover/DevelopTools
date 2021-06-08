package work.ambitlu;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 */
public class CodeGenerator {

	public static final String FILE_PATH = "/ambi-service";
	public static final String DB_URL = "jdbc:mysql://192.168.1.140:3306/BZBW?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
	public static final String DB_USERNAME = "qiyuesuo";
	public static final String DB_PASSWORD = "Admin#1234";

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator autoGenerator = new AutoGenerator();

		// 全局配置
		GlobalConfig globalConfig = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");

		//输出文件
		globalConfig.setOutputDir(projectPath+FILE_PATH  + "/src/main/java");
		globalConfig.setAuthor("Ambi");
		globalConfig.setOpen(false);

		//实体属性 Swagger2 注解
		globalConfig.setSwagger2(true);

		globalConfig.setBaseResultMap(true);

		autoGenerator.setGlobalConfig(globalConfig);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(DB_URL);
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername(DB_USERNAME);
		dsc.setPassword(DB_PASSWORD);
		autoGenerator.setDataSource(dsc);


		// 包配置
		PackageConfig packageConfig = new PackageConfig();
		//packageConfig.setModuleName(scanner("模块名"));
		packageConfig.setParent("work.ambitlu");
		autoGenerator.setPackageInfo(packageConfig);

		// 自定义配置
		InjectionConfig injectionConfig = new InjectionConfig() {
			@Override
			public void initMap() {

			}
		};


		// 如果模板引擎是 freemarker
		String templatePath = "/templates/mapper.xml.ftl";

		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath +FILE_PATH + "/src/main/resources/mapper/" + packageConfig.getModuleName()
						+ "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});


		injectionConfig.setFileOutConfigList(focList);
		autoGenerator.setCfg(injectionConfig);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();

		// 配置自定义输出模板
		//指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		//templateConfig.setEntity("templates/entity2.java");

		templateConfig.setXml(null);
		autoGenerator.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.no_change);
		strategy.setSuperEntityClass("work.ambitlu.domain.BaseEntity");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 写于父类中的公共字段
		strategy.setSuperEntityColumns("id");
		strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
		//strategy.setControllerMappingHyphenStyle(true);
		strategy.setLogicDeleteFieldName("deleted");


		autoGenerator.setStrategy(strategy);
		autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
		autoGenerator.execute();
	}

}
