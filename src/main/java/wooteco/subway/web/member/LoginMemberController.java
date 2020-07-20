package wooteco.subway.web.member;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wooteco.subway.domain.member.Member;
import wooteco.subway.service.member.MemberService;
import wooteco.subway.service.member.dto.LoginRequest;
import wooteco.subway.service.member.dto.MemberRequest;
import wooteco.subway.service.member.dto.MemberResponse;
import wooteco.subway.service.member.dto.TokenResponse;
import wooteco.subway.service.member.dto.UpdateMemberRequest;

@RestController
@RequestMapping("/api/me")
public class LoginMemberController {
	private final MemberService memberService;

	public LoginMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping
	public ResponseEntity<MemberResponse> getMemberOfMineBasic(@LoginMember Member member) {
		return ResponseEntity.ok().body(MemberResponse.of(member));
	}

	@PostMapping("/sign_up")
	public ResponseEntity<Void> createMember(@RequestBody MemberRequest view) {
		memberService.createMember(view.toMember());
		return ResponseEntity
			.created(URI.create("/login"))
			.build();
	}

	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest param) {
		String token = memberService.createToken(param);
		return ResponseEntity.ok().body(new TokenResponse(token, "bearer"));
	}

	@PutMapping
	public ResponseEntity<Void> editMemberOfMineBasic(@LoginMember Member member,
		@RequestBody UpdateMemberRequest request) {
		memberService.updateMember(member.getId(), request);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteMemberOfMineBasic(@LoginMember Member member) {
		memberService.deleteMember(member.getId());
		return ResponseEntity.ok().build();
	}
}
