package ai.nexusone.controller;
import ai.nexusone.dto.*; import ai.nexusone.enums.*; import ai.nexusone.service.EnterpriseMemoryReasoningService; import jakarta.validation.Valid; import org.springframework.data.domain.*; import org.springframework.data.web.PageableDefault; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/intelligence-memory") @CrossOrigin(origins="http://localhost:5173") public class EnterpriseMemoryReasoningController{
 private final EnterpriseMemoryReasoningService service; public EnterpriseMemoryReasoningController(EnterpriseMemoryReasoningService s){service=s;}
 @PostMapping("/create") public ResponseEntity<MemoryResponse> create(@Valid @RequestBody MemoryRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));}
 @GetMapping("/{id}") public MemoryResponse get(@PathVariable Long id){return service.get(id);}
 @GetMapping("/history") public Page<MemoryResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return service.history(p);}
 @GetMapping("/search") public Page<MemoryResponse> search(@RequestParam(required=false)String keyword,@RequestParam(required=false)MemoryType type,@RequestParam(required=false)MemoryStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC)Pageable p){return service.search(keyword,type,status,p);}
 @PostMapping("/reason") public ReasoningResponse reason(@Valid @RequestBody ReasoningRequest r){return service.reason(r);}
 @PostMapping("/{id}/validate") public MemoryResponse validate(@PathVariable Long id,@Valid @RequestBody ValidateMemoryRequest r){return service.validate(id,r);}
 @PostMapping("/{id}/reinforce") public MemoryResponse reinforce(@PathVariable Long id){return service.reinforce(id);}
 @PostMapping("/{id}/archive") public MemoryResponse archive(@PathVariable Long id){return service.archive(id);}
 @GetMapping("/analytics") public MemoryAnalyticsResponse analytics(){return service.analytics();}
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","UP","mode","ENTERPRISE_MEMORY_REASONING");}
}
