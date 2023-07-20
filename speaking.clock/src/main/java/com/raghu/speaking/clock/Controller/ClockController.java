package com.raghu.speaking.clock.Controller;

import java.time.LocalTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raghu.speaking.clock.service.ClockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ClockController {
	private final ClockService clockService;

	public ClockController(ClockService clockService) {
		this.clockService = clockService;
	}

	@Operation(summary = "Get a Time String")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Data", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "please use time in 01:00 to 23:59", content = @Content) })
	@GetMapping("/clock")
	public String getConvertInEnglish(@RequestParam LocalTime time) {
		return clockService.getConvertInEnglish(time);
	}

}
