package hello.core.member;

import java.util.HashMap;
import java.util.Map;

// DB 확정이 되지 않았기 때문에 MemoryMemberRepository 생성
public class MemoryMemberRepository implements MemberRepository{

    // 저장소 생성
    private static Map<Long, Member> store = new HashMap<>();


    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberID) {
        return store.get(memberID);
    }
}
