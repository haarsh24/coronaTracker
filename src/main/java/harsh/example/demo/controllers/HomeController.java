package harsh.example.demo.controllers;

import harsh.example.demo.models.LocationStats;
import harsh.example.demo.services.CoronaVirusDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataServices coronaVirusDataServices;

   @GetMapping("/")
   public String Home(Model model){
       List<LocationStats> allStats = coronaVirusDataServices.getAllStats();
       int totalReportedCases = allStats.stream().mapToInt(stat-> Integer.parseInt(stat.getLatestTotalCases())).sum();
       int totalNewCases = allStats.stream().mapToInt(stat-> Integer.parseInt(stat.getDiffFromPrevDay())).sum();
       model.addAttribute("locationStats",allStats);
       model.addAttribute("totalReportedCases",totalReportedCases);
       model.addAttribute("totalNewCases",totalNewCases);
     return "Home";
   }
}
