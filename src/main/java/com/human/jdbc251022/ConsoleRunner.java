package com.human.jdbc251022;

import com.human.jdbc251022.dao.MemberDao;
import com.human.jdbc251022.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

// 콘솔 입력을 만들기 위한 파일
@Component // 의존성 주입, 자동 스캔
@RequiredArgsConstructor
public class ConsoleRunner implements CommandLineRunner {
    private final Scanner sc = new Scanner(System.in);
    private final MemberDao memberDao;
    @Override
    public void run(String... args) throws Exception {
        while (true){
            System.out.println("===== 콘솔 회원 관리 시스템 =====");
            System.out.println("[1]회원 등록 [2]회원 목록 조회 [3]회원 정보 삭제");
            System.out.print("입력 : ");
            int sel = sc.nextInt();
            sc.nextLine();
            switch (sel){
                case 1:
                   boolean isSuccess =  memberDao.insertMember(regMember());
                   System.out.println("회원 가입 : " + (isSuccess ? "성공" : "실패"));
                   break;
                case 2:

                case 3:
                default:System.out.println("다시 입력해 주세요");
            }
        }
    }
    private Member regMember() {
        System.out.println("======= 회원 등록 =======");
        System.out.print("이메일 : ");
        String email = sc.nextLine();

        System.out.print("비밀번호 : ");
        String pwd = sc.nextLine();

        System.out.print("이름 : ");
        String name = sc.nextLine();
        return new Member(email, pwd, name, null);


    }
}