package be.belfius.readtag;

import be.belfius.readtag.domain.JsonLine;
import be.belfius.readtag.repository.JsonLineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "be.belfius")
@Slf4j
public class ReadtagApplication implements CommandLineRunner {
	@Autowired
	private Environment env;
	@Value("${app.name}")
	private String appName;

	@Value("${app.version}")
	private String appVersion;

	HashMap<String, String> map = new HashMap<String, String>();
	public String boDN,boVs, jsonId, key, value=null;
	private JsonLine jsonLine = new JsonLine(jsonId,  key,  value);
	@Autowired
	private JsonLineRepository repo ;
//	private JsonLineService service = new JsonLineService();

	public static void main(String[] args) {
		log.info("starting the application ");
		SpringApplication.run(ReadtagApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("EXECUTING : command line runner {}, checking directory {}"  ,env.getProperty("app.name"), env.getProperty("files.dir"));


		for (int i = 0; i < 5; ++i) {
			log.info("args[{}]: {}", i, i);
		}
//		"D:\\Data\\file1"
//		"D:\\Data\\17L07462.txt"
//		"D:\\Data\\19L03549.txt"
//		"D:\\Data\\19L03553.txt"
//		"D:\\Data\\20L00003.txt"
//		"D:\\Data\\20L00029.txt"
		try {
			List<String> allLines = Files.readAllLines(Paths.get("D:\\Data\\20L00029.txt"));
			for (String line : allLines) {
				System.out.println(line);
				String[] parts = line.split("=", 2);
				log.info("split {} = {}",parts[0].substring(1,parts[0].length()-1), parts[1]);
				if (parts.length >= 2)
				{
					String key = parts[0].substring(1,parts[0].length()-1);
					String value = parts[1];
					map.put(key, value);
					if (key.equals("boDealNumber")){
						boDN=value;
						log.info("boDN={}",value);
					}
					if (key.equals("boDealVersion")){
						boVs=value;
						log.info("boVs={}",value);
					}
				} else {
					System.out.println("ignoring line: " + line);
				}

			}
			for (String line : allLines) {
				String[] parts = line.split("=", 2);
//				log.info("split {} = {}",parts[0].substring(1,parts[0].length()-1), parts[1]);
				if (parts.length >= 2)
				{
					jsonLine = new JsonLine();
					jsonLine.setJsonId(boDN+boVs);
					log.info("ts={}",LocalDateTime.now());
					jsonLine.setSqlTimestamp(java.sql.Timestamp.valueOf(LocalDateTime.now()));
					jsonLine.setKey(parts[0].substring(1,parts[0].length()-1))  ;
					jsonLine.setValue( parts[1]) ;
					repo.save(jsonLine);
//					repo.save(new JsonLine(java.sql.Timestamp.valueOf(LocalDateTime.now()),boDN,parts[0].substring(1,parts[0].length()-1), parts[1]));
//					service.createJsonLine(jsonLine);
					log.info("save {} = {}",parts[0].substring(1,parts[0].length()-1), parts[1]);
				} else {
					System.out.println("ignoring line: " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
