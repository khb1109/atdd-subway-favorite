package wooteco.subway.domain.member;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import wooteco.subway.domain.BaseEntity;

@Entity
@Getter
@Setter
public class Member extends BaseEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String name;
	private String password;

	public Member() {
	}

	public Member(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public Member(Long id, String email, String name, String password) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public void update(String name, String password) {
		if (StringUtils.isNotBlank(name)) {
			this.name = name;
		}
		if (StringUtils.isNotBlank(password)) {
			this.password = password;
		}
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Member member = (Member)o;
		return Objects.equals(id, member.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Member{" +
			"id=" + id +
			", email='" + email + '\'' +
			", name='" + name + '\'' +
			", password='" + password + '\'' +
			'}';
	}
}
