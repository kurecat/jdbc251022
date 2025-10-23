package com.human.jdbc251022;

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
    private final Input input;
    @Override
    public void run(String... args) throws Exception {
        while (true){
            System.out.println("===== MES DB =====");
            System.out.println("[1]조회 [2]등록 [3]수정 [4]종료");
            System.out.print("입력 : ");
            int sel = sc.nextInt();
            sc.nextLine();
            switch (sel){
                case 1:
                    input.checkTable(); break;
                case 2:
                    input.insertTable(); break;
                case 3:
                    input.updateTable();break;
                case 4:
                    System.exit(0);
                    System.out.println("프로그램을 종료합니다.");
                default:System.out.println("다시 입력해 주세요");

            }
        }
    }
}