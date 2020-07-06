package wooteco.subway.service.member;

import org.springframework.stereotype.Service;

import wooteco.subway.domain.member.Member;
import wooteco.subway.domain.member.MemberRepository;
import wooteco.subway.exception.DuplicateEmailException;
import wooteco.subway.exception.NoSuchAccountException;
import wooteco.subway.exception.WrongPasswordException;
import wooteco.subway.infra.JwtTokenProvider;
import wooteco.subway.service.member.dto.LoginRequest;
import wooteco.subway.service.member.dto.UpdateMemberRequest;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	private final JwtTokenProvider jwtTokenProvider;

	public MemberService(MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider) {
		this.memberRepository = memberRepository;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	public Member createMember(Member member) {
		try {
			return memberRepository.save(member);
		} catch (Exception e) { // todo : 변경
			throw new DuplicateEmailException();
		}
	}

	public void updateMember(Long id, UpdateMemberRequest param) {
		Member member = memberRepository.findById(id).orElseThrow(RuntimeException::new);
		member.update(param.getName(), param.getPassword());
		memberRepository.save(member);
	}

	public void deleteMember(Long id) {
		memberRepository.deleteById(id);
	}

	public String createToken(LoginRequest param) {
		Member member = memberRepository.findByEmail(param.getEmail())
			.orElseThrow(NoSuchAccountException::new);
		if (!member.checkPassword(param.getPassword())) {
			throw new WrongPasswordException();
		}
		return jwtTokenProvider.createToken(param.getEmail());
	}

	public Member findMemberByEmail(String email) {
		return memberRepository.findByEmail(email).orElseThrow(NoSuchAccountException::new);
	}
}
