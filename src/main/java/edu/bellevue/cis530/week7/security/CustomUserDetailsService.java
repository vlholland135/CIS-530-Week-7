package edu.bellevue.cis530.week7.security;

import edu.bellevue.cis530.week7.entity.Member;
import edu.bellevue.cis530.week7.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Member not found: " + username));

        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(member.getRole()));

        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .disabled(!Boolean.TRUE.equals(member.getEnabled()))
                .authorities(authorities)
                .build();
    }
}