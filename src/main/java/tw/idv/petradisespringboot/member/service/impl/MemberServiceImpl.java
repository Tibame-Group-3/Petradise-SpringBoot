package tw.idv.petradisespringboot.member.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.email.EmailService;
import tw.idv.petradisespringboot.member.exceptions.AccountAlreadyExistsException;
import tw.idv.petradisespringboot.member.repo.EmailVerificationRepository;
import tw.idv.petradisespringboot.member.repo.MemberRepository;
import tw.idv.petradisespringboot.member.service.MemberService;
import tw.idv.petradisespringboot.member.vo.EmailVerification;
import tw.idv.petradisespringboot.member.vo.Member;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    private final EmailService emailService;

    private final EmailVerificationRepository emailVerificationRepository;

    MemberServiceImpl(MemberRepository repository, EmailService emailService, EmailVerificationRepository emailVerificationRepository) {
        this.repository = repository;
        this.emailService = emailService;
        this.emailVerificationRepository = emailVerificationRepository;
    }

    @Override
    public Optional<Member> login(String account, String password) {
        return repository.findByAccountAndPassword(account, password);
    }

    @Transactional
    @Override
    public Member signUp(Member newMember) {
        if (repository.findByAccount(newMember.getAccount()).isPresent()) {
            throw new AccountAlreadyExistsException("Account already exists: " + newMember.getAccount());
        }
        var member = repository.save(newMember);
        String token = saveEmailVerification(member);
        sendVerificationEmail(member.getEmail(), token);
        return member;
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
    public List<Member> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Member> getById(Integer id) {
        return repository
                .findById(id);
    }

    @Override
    public Member update(Member newMember) {
        return repository.save(newMember);
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
}

