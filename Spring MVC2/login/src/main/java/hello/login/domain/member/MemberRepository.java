package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    // 저장소
    private static Map<Long, Member> store = new HashMap<>(); // static 사용
    private static long sequence = 0L; // static 사용

    // 회원 가입
    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member); // id로 member를 찾고, member를 저장소에 저장
        return member;
    }

    // 회원 찾기
    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream().filter(m -> m.getLoginId().equals(loginId)).findFirst();
    }

    // 전체 회원 조회
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 초기화
    public void clearStore() {
        store.clear();
    }
}
