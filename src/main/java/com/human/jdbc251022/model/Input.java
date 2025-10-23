package com.human.jdbc251022.model;

import com.human.jdbc251022.dao.MemberDao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
@Component

public class Input {
    private final Scanner sc;
    private final MemberDao memberDao;

    public Input(MemberDao memberDao) {
        this.memberDao = memberDao;
        this.sc = new Scanner(System.in); // Scanner는 여기서 직접 생성
    }

    public void infoMember() { // 조회
        List<Member> memberList = memberDao.memberList();
        for (Member member : memberList) System.out.println(member);
    }

//    public void deleteMember(){ // 회원 정보 삭제
//        System.out.println("삭제할 회원의 이메일을 입력해 주세요");
//        System.out.print("입력 : ");
//        String email = sc.nextLine();
//
//        Member member = new Member();
//        member.setEmail(email);
//
//        boolean deSuccess = memberDao.deleteMember(member);
//        System.out.println("회원 정보 삭제 : " + (deSuccess ? "성공" : "실패"));
//    }

//    public void regMember() { // 회원 등록
//        Member member = new Member();
//        System.out.println("======= 회원 등록 =======");
//        System.out.print("이메일 : ");
//        String email = sc.nextLine();
//
//        System.out.print("비밀번호 : ");
//        String pwd = sc.nextLine();
//
//        System.out.print("이름 : ");
//        String name = sc.nextLine();
//        new Member(email, pwd, name, null);
//
//        boolean inSuccess =  memberDao.insertMember(member);
//        System.out.println("회원 가입 : " + (inSuccess ? "성공" : "실패"));
//    }

//    public void processUpdateMember(){ // 회원 정보 수정
//        System.out.println("======= 회원 정보 수정 =======");
//        System.out.println("회원님의 이메일을 입력해 주세요");
//        System.out.print("입력 : ");
//        String email = sc.nextLine();
//
//
//        System.out.println("수정하실 정보를 선택해 주세요 [1]비밀번호 [2]이름");
//        System.out.print("입력 : ");
//        int choice = sc.nextInt();
//        sc.nextLine();
//
//        Member member = new Member();
//        member.setEmail(email);
//
//        boolean isSuccess = false;
//
//        switch (choice){
//            case 1:
//                System.out.print("새 비밀번호 입력 : ");
//                String newPwd = sc.nextLine();
//                member.setPwd(newPwd);
//                isSuccess = memberDao.updatePwdMember(member);
//                break;
//            case 2:
//                System.out.print("새 이름 입력 : ");
//                String newName = sc.nextLine();
//                member.setName(newName);
//                isSuccess = memberDao.updateNameMember(member);
//                break;
//            default:
//                System.out.println("잘못된 선택입니다. 수정을 취소합니다.");
//                return;
//        }
//
//        System.out.println("회원 정보 수정 : " + (isSuccess ? "성공" : "실패"));
//    }
}



