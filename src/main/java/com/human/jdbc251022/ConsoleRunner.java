package com.human.jdbc251022;

import com.human.jdbc251022.dao.MemberDao;
import com.human.jdbc251022.model.Input;
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
    private final Input input;
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
//                   input.regMember();
                   break;
                case 2:
                   input.infoMember(); break;
                case 3:
//                    input.deleteMember();
                    break;
                case 4:
//                    input.processUpdateMember();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다."); return;
                default:System.out.println("다시 입력해 주세요");

            }
        }
    }
}