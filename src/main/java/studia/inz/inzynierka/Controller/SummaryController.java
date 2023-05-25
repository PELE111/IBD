package studia.inz.inzynierka.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import studia.inz.inzynierka.DTO.SummaryDto;
import studia.inz.inzynierka.Service.SummaryService;

import java.time.LocalDate;
import java.sql.Date;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/summary")
public class SummaryController {

    private final SummaryService summaryService;

    @GetMapping
    ResponseEntity<SummaryDto> getSummary(Authentication authentication, @RequestParam("date")  Date date){
        return summaryService.getSummary(authentication, date);
    }

    @PatchMapping
    ResponseEntity updateSummary(Authentication authentication, @RequestParam("date")  Date date){
        summaryService.updateSummary(authentication.getName(), date);
        return ResponseEntity.ok().build();
    }
}