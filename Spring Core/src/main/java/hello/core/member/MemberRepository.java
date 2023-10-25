package hello.core.member;

public interface MemberRepository {
    
    // 회원 가입(저장)
    void save(Member member);

    // 회원 조회
    Member findById(Long memberID);
}
