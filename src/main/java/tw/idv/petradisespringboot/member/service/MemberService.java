package tw.idv.petradisespringboot.member.service;

import tw.idv.petradisespringboot.member.dto.MemberDTO;
import tw.idv.petradisespringboot.member.dto.SignUpDTO;
import tw.idv.petradisespringboot.member.dto.UpdateDTO;
import tw.idv.petradisespringboot.member.vo.AddressInfo;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Optional<MemberDTO> login(String account, String password);

    MemberDTO signUp(SignUpDTO dto);

    List<MemberDTO> getAll();

    Optional<MemberDTO> getById(Integer id);

    MemberDTO update(UpdateDTO dto);

    String changePassword(Integer id, String oldPassword, String newPassword);

    boolean verifyEmail(String token);

    // 註冊時地址縣市/地區下拉選單
    List<AddressInfo> getAddressInfo() throws Exception;
}
