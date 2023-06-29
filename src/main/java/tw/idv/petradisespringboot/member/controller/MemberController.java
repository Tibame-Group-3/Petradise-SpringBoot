package tw.idv.petradisespringboot.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.member.dto.ChangePasswordDTO;
import tw.idv.petradisespringboot.member.dto.LoginDTO;
import tw.idv.petradisespringboot.member.dto.SignUpDTO;
import tw.idv.petradisespringboot.member.dto.UpdateDTO;
import tw.idv.petradisespringboot.member.service.MemberService;

import java.util.Objects;

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
        try {
            var newMember = service.signUp(dto);
            return ResponseEntity.ok(newMember);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/id={id}")
    ResponseEntity<?> one(@PathVariable Integer id) {
        var member = service.getById(id);
        if (member.isPresent()) {
            return ResponseEntity.ok(member.get());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new MemberNotFoundException(id));
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        var foundMember = service.login(dto.getAccount(), dto.getPassword());
        if (foundMember.isPresent()) {
            // Only return the id of the member
            var isVerified = foundMember.get().getIsEmailVerified();
            if (!isVerified) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new MemberNotFoundException("帳號信箱尚未驗證"));
            }
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();
            node.put("id", foundMember.get().getId());
            return ResponseEntity.ok(node);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new MemberNotFoundException("帳號或密碼有誤"));
    }

    @GetMapping("/districts")
    ResponseEntity<?> districts() throws Exception {
        return ResponseEntity.ok(service.getAddressInfo());
    }

    @PostMapping("/change-password")
    ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO dto) {
        final var result = service.changePassword(dto.getId(), dto.getOldPassword(), dto.getNewPassword());
        if (Objects.equals(result, "更改密碼成功")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(result);
    }

    @GetMapping("/verify-email")
    ResponseEntity<?> verifyEmail(@RequestParam String token) {
        final var result = service.verifyEmail(token);
        if (result) {
            return ResponseEntity.ok("驗證成功");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("驗證失敗");
    }
}


class MemberNotFoundException extends RuntimeException {
    MemberNotFoundException(Integer id) {
        super("找不到會員ID: " + id);
    }

    MemberNotFoundException(String message) {
        super(message);
    }
}

