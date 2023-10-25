package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 생성자 생성
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

     /* join에서 save 호출 시, 다형성에 의하여
     MemberRepository 인터페이스가 아닌
     MemoryMemberRepository에 있는 save 호출 */

    // 회원 가입
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    // 회원 조회
    @Override
    public Member findMember(Long memberID) {
        return memberRepository.findById(memberID);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
