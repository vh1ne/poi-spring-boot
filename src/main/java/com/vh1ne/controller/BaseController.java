package com.vh1ne.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vh1ne.service.ExcelReader;
import com.vh1ne.service.SaxEventUserModel;

@RestController
@RequestMapping("/v1")

public class BaseController {

	@GetMapping(value = "/poi/new", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> poi_new(@RequestParam("path") Optional<String> path) {
		String fileName = "";
		String resp = "";
		try {
			if (path.isPresent())
				fileName = path.get();
			else
				fileName = "E:\\Downloads\\Chrome Downloads\\Sample_900K.xlsx";
			// ""E:\\Downloads\\Chrome Downloads\\Sample10K.xlsx";
			SaxEventUserModel saxEventUserModel = new SaxEventUserModel();

			resp = saxEventUserModel.processSheets(fileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Resp  " + resp);
	}

	@GetMapping(value = "/poi/old", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> poi_old(@RequestParam("path") Optional<String> path) {
		String fileName = "";
		String resp = "";
		try {
			if (path.isPresent())
				fileName = path.get();
			else
				fileName = "E:\\Downloads\\Chrome Downloads\\Sample_900K.xlsx";
			resp = ExcelReader.method(fileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Resp  " + resp);
	}

}
