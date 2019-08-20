package com.example.matchtimeconverter;

import com.example.matchtimeconverter.service.MatchTimeTransformerService;
import com.example.matchtimeconverter.util.FileReaderUtil;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MatchTimeConverterApplication implements CommandLineRunner {

	private FileReaderUtil fileReaderUtil;
	private MatchTimeTransformerService matchTimeTransformerService;

	@Autowired
	public MatchTimeConverterApplication(FileReaderUtil fileReaderUtil,
			MatchTimeTransformerService matchTimeTransformerService) {
		this.fileReaderUtil = fileReaderUtil;
		this.matchTimeTransformerService = matchTimeTransformerService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MatchTimeConverterApplication.class, args);
	}

	@Override
	public void run(String[] args) {

		List<String> argsList = Arrays.stream(args).collect(Collectors.toList());

		for (String itemInList : argsList) {
			fileReaderUtil.readFile(itemInList)
					.stream()
					.map(matchTimeTransformerService::transformMatchTime)
					.forEach(System.out::println);
		}
	}

}
