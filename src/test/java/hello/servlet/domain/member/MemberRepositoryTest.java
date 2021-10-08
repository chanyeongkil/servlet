package hello.servlet.domain.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Target;
import java.util.List;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        // given
        Member member = new Member("hello", 20);

        // when
        Member saveMember = memberRepository.save(member);

        // then
        Member findMember = memberRepository.findById(saveMember.getId());

        Assertions.assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findAll(){
        // given
        Member member1 = new Member("hello", 20);
        Member member2 = new Member("olleh", 99);

        // when
        memberRepository.save(member1);
        memberRepository.save(member2);

        // then
        List<Member> allMembers = memberRepository.findAll();

        Assertions.assertThat(allMembers.size()).isEqualTo(2);
        Assertions.assertThat(allMembers).contains(member1, member2);
    }
}
