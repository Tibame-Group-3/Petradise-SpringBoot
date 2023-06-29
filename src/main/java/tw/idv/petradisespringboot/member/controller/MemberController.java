package tw.idv.petradisespringboot.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.member.dto.ChangePasswordDTO;
import tw.idv.petradisespringboot.member.dto.LoginDTO;
import tw.idv.petradisespringboot.member.dto.SignUpDTO;
import tw.idv.petradisespringboot.member.dto.UpdateDTO;
import tw.idv.petradisespringboot.member.service.MemberService;

@RestController
@RequestMapping("/members")
class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/all")
    ResponseEntity<?> all() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/update")
    ResponseEntity<?> update(@RequestBody UpdateDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @PostMapping("/sign-up")
    ResponseEntity<?> signUp(@RequestBody SignUpDTO dto) {
        return ResponseEntity.ok(service.signUp(dto));
    }

    @GetMapping("/id={id}")
    ResponseEntity<?> one(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        var member = service.login(dto.getAccount(), dto.getPassword());
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("id", member.getId());
        return ResponseEntity.ok(node);
    }

    @GetMapping("/districts")
    ResponseEntity<?> districts() throws Exception {
        return ResponseEntity.ok(service.getAddressInfo());
    }

    @PostMapping("/change-password")
    ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO dto) {
        service.changePassword(dto.getId(), dto.getOldPassword(), dto.getNewPassword());
        return ResponseEntity.ok("密碼修改成功");
    }

    @GetMapping("/verify-email")
    ResponseEntity<?> verifyEmail(@RequestParam String token) {
        service.verifyEmail(token);
        return ResponseEntity
                .ok("驗證成功");
    }
}


