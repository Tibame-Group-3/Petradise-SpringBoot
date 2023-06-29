package tw.idv.petradisespringboot.member.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.email.EmailService;
import tw.idv.petradisespringboot.member.dto.MemberDTO;
import tw.idv.petradisespringboot.member.dto.SignUpDTO;
import tw.idv.petradisespringboot.member.dto.UpdateDTO;
import tw.idv.petradisespringboot.member.exceptions.AccountAlreadyExistsException;
import tw.idv.petradisespringboot.member.repo.EmailVerificationRepository;
import tw.idv.petradisespringboot.member.repo.MemberRepository;
import tw.idv.petradisespringboot.member.service.MemberService;
import tw.idv.petradisespringboot.member.vo.AddressInfo;
import tw.idv.petradisespringboot.member.vo.EmailVerification;
import tw.idv.petradisespringboot.member.vo.Member;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    private final ModelMapper mapper;

    private final EmailService emailService;

    private final ResourceLoader resourceLoader;

    private final EmailVerificationRepository emailVerificationRepository;

    MemberServiceImpl(MemberRepository repository,
                      EmailService emailService,
                      ResourceLoader resourceLoader,
                      EmailVerificationRepository emailVerificationRepository,
                      ModelMapper mapper) {
        this.repository = repository;
        this.emailService = emailService;
        this.resourceLoader = resourceLoader;
        this.emailVerificationRepository = emailVerificationRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<MemberDTO> login(String account, String password) {
        return repository
                .findByAccountAndPassword(account, password)
                .map(member -> mapper.map(member, MemberDTO.class));
    }

    @Transactional
    @Override
    public MemberDTO signUp(SignUpDTO dto) {
        Member newMember = mapper.map(dto, Member.class);
        if (repository.findByAccount(newMember.getAccount()).isPresent()) {
            throw new AccountAlreadyExistsException("Account already exists: " + newMember.getAccount());
        }
        var member = repository.save(newMember);
        String token = saveEmailVerification(member);
        sendVerificationEmail(member.getEmail(), token);
        return mapper.map(member, MemberDTO.class);
    }

    private String saveEmailVerification(Member member) {
        String token = UUID.randomUUID().toString();
        EmailVerification verification = new EmailVerification(member.getId(), token);
        emailVerificationRepository.save(verification);
        return token;
    }

    private void sendVerificationEmail(String emailAddress, String token) {
        String subject = "請驗證您的電子郵件";
        String text = "請點擊以下連結驗證您的電子郵件: http://localhost:8080/member/verify.html?token=" + token;
        emailService.sendEmail(emailAddress, subject, text);
    }

    @Override
    public List<MemberDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(m -> mapper.map(m, MemberDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MemberDTO> getById(Integer id) {
        return repository
                .findById(id)
                .map(m -> mapper.map(m, MemberDTO.class));
    }

    @Override
    public MemberDTO update(UpdateDTO dto) {
        Optional<Member> memberOptional = repository.findById(dto.getId());
        if (memberOptional.isEmpty()) {
            throw new RuntimeException("找不到此用戶");
        }
        var member = memberOptional.get();
        member.setName(dto.getName());
        member.setPhone(dto.getPhone());
        member.setEmail(dto.getEmail());
        member.setAddress(dto.getAddress());
        member.setBirthday(dto.getBirthday());
        var saved = repository.save(member);
        return mapper.map(saved, MemberDTO.class) ;
    }

    @Override
    public String changePassword(Integer id, String oldPassword, String newPassword) {

        var optionalMember = repository.findById(id);
        if (optionalMember.isEmpty()) {
            return "找不到此用戶";
        }
        var member = optionalMember.get();
        if (!Objects.equals(member.getPassword(), oldPassword)) {
            return "密碼錯誤";
        }
        member.setPassword(newPassword);
        repository.save(member);
        return "更改密碼成功";
    }

    @Transactional
    @Override
    public boolean verifyEmail(String token) {
        var verification = emailVerificationRepository.findByToken(token);
        if (verification.isPresent()) {
            var member = repository.findById(verification.get().getMemberId());
            member.ifPresent(m -> {
                m.setIsEmailVerified(true);
                repository.save(m);
            });
            emailVerificationRepository.delete(verification.get());
            return true;
        }
        return false;
    }

    @Override
    public List<AddressInfo> getAddressInfo() throws IOException {
        Resource resource = loadDistricts();
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructParametricType(List.class, AddressInfo.class);
        return mapper.readValue(resource.getInputStream(), type);
    }

    private Resource loadDistricts(){
        return resourceLoader.getResource("classpath:json/address_info.json");
    }

}

