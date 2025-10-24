package com.human.jdbc251022;

import com.human.jdbc251022.model.TotalInput;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

// 콘솔 입력을 만들기 위한 파일
@Component // 의존성 주입, 자동 스캔
@RequiredArgsConstructor
public class ConsoleRunner implements CommandLineRunner {
    private final Scanner sc = new Scanner(System.in);
    private final TotalInput totalInput;
    @Override
    public void run(String... args) throws Exception {
        while (true){
            System.out.println("===== MES DB =====");
            System.out.println("[1]제품관리 [2]생산관리 [3]품질관리 [4]설비/공정관리 [5]자재관리 [6]부서/사원관리 [0]종료");
            System.out.print("입럭 : ");
            int c = sc.nextInt();
            sc.nextLine();
            switch (c) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    totalInput.empdepttotalinput();
                    break;
                case 7:
                    break;
                case 0: return;

            }
        }
    }
}