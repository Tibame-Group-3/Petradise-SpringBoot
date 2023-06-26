package tw.idv.petradisespringboot.member.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.member.service.MemberService;
import tw.idv.petradisespringboot.member.vo.AddressInfo;
import tw.idv.petradisespringboot.member.vo.Member;

import java.util.List;

@RestController
@RequestMapping("/members")
class MemberController {

    private final MemberService service;

    private final ResourceLoader resourceLoader;

    public MemberController(MemberService service, ResourceLoader resourceLoader) {
        this.service = service;
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/all")
    ResponseEntity<?> all() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/sign-up")
    ResponseEntity<?> signUp(@RequestBody Member member) {
        try {
            var newMember = service.signUp(member);
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
    ResponseEntity<?> login(@RequestBody Member member) {
        var account = member.getAccount();
        var password = member.getPassword();
        var foundMember = service.login(account, password);
        if (foundMember.isPresent()) {
            // Only return the id of the member
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
        Resource resource = loadDistricts();
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructParametricType(List.class, AddressInfo.class);
        List<AddressInfo> infos = mapper.readValue(resource.getInputStream(), type);
        return ResponseEntity.ok(infos);
    }

    @PostMapping("/change-password")
    ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO dto) {
        return service.changePassword(dto.getId(), dto.getOldPassword(), dto.getNewPassword()) ?
                ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private Resource loadDistricts(){
        return resourceLoader.getResource("classpath:json/address_info.json");
    }


    @Data
    private class ChangePasswordDTO {
        Integer id;
        String oldPassword;
        String newPassword;
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

