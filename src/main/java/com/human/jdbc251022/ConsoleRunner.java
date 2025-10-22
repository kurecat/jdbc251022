package com.human.jdbc251022;

import com.human.jdbc251022.dao.MemberDao;
import com.human.jdbc251022.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
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
            System.out.println("[1]회원 등록 [2]회원 목록 조회 [3]회원 정보 삭제 [4]회원 정보 수정 [0]종료");
            System.out.print("입력 : ");
            int sel = sc.nextInt();
            sc.nextLine();
            switch (sel){
                case 1:
                   boolean inSuccess =  memberDao.insertMember(regMember());
                   System.out.println("회원 가입 : " + (inSuccess ? "성공" : "실패"));
                   break;
                case 2:
                    List<Member> memberList = memberDao.memberList();
                    for (Member member : memberList) System.out.println(member);
                    break;
                case 3:
                    boolean deSuccess = memberDao.deleteMember(deleteMember());
                    System.out.println("회원 정보 삭제 : " + (deSuccess ? "성공" : "실패"));
                    break;
                case 4:
                    processUpdateMember();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
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

    private void processUpdateMember(){
        System.out.println("======= 회원 정보 수정 =======");
        System.out.println("회원님의 이메일을 입력해 주세요");
        System.out.print("입력 : ");
        String email = sc.nextLine();

        System.out.println("수정하실 정보를 선택해 주세요 [1]비밀번호 [2]이름");
        System.out.print("입력 : ");
        int choice = sc.nextInt();
        sc.nextLine();

        Member member = new Member();
        member.setEmail(email);

        boolean isSuccess = false;

        switch (choice){
            case 1:
                System.out.print("새 비밀번호 입력 : ");
                String newPwd = sc.nextLine();
                member.setPwd(newPwd);
                isSuccess = memberDao.updatePwdMember(member);
                break;
            case 2:
                System.out.print("새 이름 입력 : ");
                String newName = sc.nextLine();
                member.setName(newName);
                isSuccess = memberDao.updateNameMember(member);
                break;
            default:
                System.out.println("잘못된 선택입니다. 수정을 취소합니다.");
                return;
        }

        System.out.println("회원 정보 수정 : " + (isSuccess ? "성공" : "실패"));
    }

    private Member deleteMember(){
        System.out.println("삭제할 회원의 이메일을 입력해 주세요");
        System.out.print("입력 : ");
        String email = sc.nextLine();

        Member member = new Member();
        member.setEmail(email);

        return member;
    }
}