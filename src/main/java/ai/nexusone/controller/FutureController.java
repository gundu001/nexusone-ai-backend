package ai.nexusone.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/future")
public class FutureController {
 @GetMapping("/capabilities")
 public List<Map<String,String>> capabilities(){
  return List.of(Map.of("key","AI_RCA","status","PLANNED"),
          Map.of("key","SELF_HEALING","status","PLANNED"),
          Map.of("key","GIT_INTEGRATION","status","PLANNED"),
          Map.of("key","KUBERNETES_EXECUTION","status","PLANNED"),
          Map.of("key","MULTI_CLOUD","status","PLANNED"),
          Map.of("key","FINOPS","status","PLANNED"));
 }
}
